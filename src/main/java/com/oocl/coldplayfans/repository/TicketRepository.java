package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.dao.Ticket;

import java.util.List;

public interface TicketRepository {

    public List<Ticket> getTicketsByConcertId(Integer concertId);
}
