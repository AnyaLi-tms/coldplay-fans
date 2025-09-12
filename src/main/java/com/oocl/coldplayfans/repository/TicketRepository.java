package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.model.Ticket;

import java.util.List;

public interface TicketRepository {

    public List<Ticket> getTicketsByConcertId(Integer concertId);

    public List<Ticket> findTicketByIdNumsAndConcertId(Integer concertId, List<String> idNums);

    public List<Ticket> findEmptyIdNumberWithLock(Integer concertId, String seatArea, Integer count);

    public List<Ticket> saveAll(List<Ticket> tickets);

    public List<Ticket> getTicketByUserId(int userId);
}
