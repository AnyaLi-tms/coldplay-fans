package com.oocl.coldplayfans.service;

import com.oocl.coldplayfans.model.Concert;
import com.oocl.coldplayfans.model.Order;
import com.oocl.coldplayfans.model.Ticket;
import com.oocl.coldplayfans.dto.Price;
import com.oocl.coldplayfans.dto.TicketOrderResponse;
import com.oocl.coldplayfans.dto.TicketPricesReponse;
import com.oocl.coldplayfans.dto.UserTicketOrderReponse;
import com.oocl.coldplayfans.repository.ConcertDbRepository;
import com.oocl.coldplayfans.repository.OrderDbRepository;
import com.oocl.coldplayfans.repository.TicketDbRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    private TicketDbRepository ticketDbRepository;
    @Autowired
    private OrderDbRepository orderDbRepository;
    @Autowired
    private ConcertDbRepository concertDbRepository;

    private final String TICKET = "ticket";

    public TicketPricesReponse getPriceListByConcert(Integer concertId) {
        List<Ticket> tickets = ticketDbRepository.getTicketsByConcertId(concertId);
        List<Price> priceList = tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getSeatArea))
                .values().stream()
                .map(areaTickets -> {
                    Price price = new Price();
                    price.setPrice(areaTickets.getFirst().getPrice());
                    price.setSeatArea(areaTickets.getFirst().getSeatArea());
                    long remainCount = areaTickets.stream()
                            .filter(ticket -> ticket.getUserId() == null)
                            .count();
                    price.setRemainNumber((int) remainCount);
                    return price;
                })
                .collect(Collectors.toList());
        TicketPricesReponse response = new TicketPricesReponse();
        response.setPriceList(priceList);
        return response;
    }

    public List<Ticket> findTicketByIdNumsAndConcertId(Integer concertId, List<String> idNums) {
        return ticketDbRepository.findTicketByIdNumsAndConcertId(concertId, idNums);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Ticket> buyTickets(Integer userId, String seatArea, List<String> idNums, Integer concertId) {
        List<Ticket> tickets = ticketDbRepository.findEmptyIdNumberWithLock(concertId, seatArea, idNums.size());
        if (tickets.size() < idNums.size()) {
            throw new RuntimeException("余票不足，请重试");
        }
        Order order = new Order(TICKET, null);
        Order newOreder = orderDbRepository.createOrder(order);
        for(int i = 0; i < tickets.size(); i++){
            Ticket ticket = tickets.get(i);
            ticket.setUserId(userId);
            ticket.setIdNumber(idNums.get(i));
            LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
            ticket.setPurchaseDate(now);
            ticket.setOrderId(newOreder.getId());
        }
        return ticketDbRepository.saveAll(tickets);
    }

    public List<UserTicketOrderReponse> loadTicketOrders(int userId) {
        List<Ticket> userTickets = ticketDbRepository.getTicketByUserId(userId);
        Map<Integer, List<Ticket>> ticketListByOrderId = userTickets.stream().collect(Collectors.groupingBy(Ticket::getOrderId));
        List<UserTicketOrderReponse> userTicketOrderList = new ArrayList<>();
        for(List<Ticket> tickets : ticketListByOrderId.values()){
            Integer orderId = tickets.getFirst().getOrderId();
            Concert concert = concertDbRepository.getConcertById(tickets.getFirst().getConcertId());
            Integer amount = tickets.size();
            String imgUrl = concert.getImgUrl();
            BigDecimal totalPrice = tickets.stream()
                    .map(Ticket::getPrice)
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            LocalDateTime purchaseDate = tickets.getFirst().getPurchaseDate();
            List<TicketOrderResponse> ticketOrderResponses = new ArrayList<>();
            for(Ticket ticket : tickets){
                TicketOrderResponse ticketOrderResponse = new TicketOrderResponse(ticket.getSeatNumber(), ticket.getIdNumber());
                ticketOrderResponses.add(ticketOrderResponse);
            }
            UserTicketOrderReponse userTicketOrderReponse = new UserTicketOrderReponse(orderId, concert.getName(), amount, totalPrice, purchaseDate, imgUrl, "交易完成", ticketOrderResponses);
            userTicketOrderList.add(userTicketOrderReponse);
        }
        return userTicketOrderList;
    }
}
