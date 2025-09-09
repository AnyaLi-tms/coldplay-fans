package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.dao.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketDbRepository {

    @Autowired
    private JpaTicketRepository jpaTicketRepository;

    public List<Ticket> getTicketsByConcertId(Integer concertId) {
        return jpaTicketRepository.findTicketsByConcertId(concertId);
    }

    public List<Ticket> findTicketByIdNumsAndConcertId(Integer concertId, List<String> idNums, String seatArea) {
        return jpaTicketRepository.findByConcertIdAndSeatAreaAndIdNumberIn(concertId, seatArea, idNums);
    }

    public List<Ticket> findEmptyIdNumberWithLock(Integer concertId, String seatArea, Integer count) {
        return jpaTicketRepository.findEmptyIdNumberWithLock(concertId, seatArea, count);
    }

    public List<Ticket> saveAll(List<Ticket> tickets) {
        return jpaTicketRepository.saveAll(tickets);
    }
}
