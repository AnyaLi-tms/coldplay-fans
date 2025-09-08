package com.oocl.coldplayfans.service;

import com.oocl.coldplayfans.dao.Ticket;
import com.oocl.coldplayfans.dto.Price;
import com.oocl.coldplayfans.dto.TicketPricesReponse;
import com.oocl.coldplayfans.repository.TicketDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    private TicketDbRepository ticketDbRepository;

    public TicketPricesReponse getPriceListByConcert(Integer concertId) {
        List<Ticket> tickets = ticketDbRepository.getTicketsByConcertId(concertId);
        List<Price> priceList = tickets.stream()
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
}
