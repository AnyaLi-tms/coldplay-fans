package com.oocl.coldplayfans.service;

import com.oocl.coldplayfans.dao.Ticket;
import com.oocl.coldplayfans.dto.Price;
import com.oocl.coldplayfans.dto.TicketPricesReponse;
import com.oocl.coldplayfans.repository.TicketDbRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    private TicketDbRepository ticketDbRepository;

    public TicketPricesReponse getPriceListByConcert(Integer concertId) {
        List<Ticket> tickets = ticketDbRepository.getTicketsByConcertId(concertId);
        List<Price> priceList = tickets.stream().filter(ticket -> ticket.getUserId() == null && StringUtils.isEmpty(ticket.getIdNumber()))
                .collect(Collectors.groupingBy(Ticket::getSeatArea))
                .values().stream()
                .map(areaTickets -> {
                    Price price = new Price();
                    price.setPrice(areaTickets.getFirst().getPrice());
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

    public List<Ticket> findTicketByIdNumsAndConcertId(Integer concertId, List<String> idNums, String seatArea) {
        return ticketDbRepository.findTicketByIdNumsAndConcertId(concertId, idNums, seatArea);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Ticket> buyTickets(Integer userId, String seatArea, List<String> idNums, Integer concertId) {
        List<Ticket> tickets = ticketDbRepository.findEmptyIdNumberWithLock(concertId, seatArea, idNums.size());
        if (tickets.size() < idNums.size()) {
            throw new RuntimeException("余票不足，请重试");
        }
        for(int i = 0; i < tickets.size(); i++){
            Ticket ticket = tickets.get(i);
            ticket.setUserId(userId);
            ticket.setIdNumber(idNums.get(i));
        }
        return ticketDbRepository.saveAll(tickets);
    }
}
