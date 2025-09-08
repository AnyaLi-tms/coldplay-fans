package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.dao.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaTicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findTicketsByConcertId(Integer concertId);
}
