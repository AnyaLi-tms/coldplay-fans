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
}
