package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.dao.Ticket;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaTicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findTicketsByConcertId(Integer concertId);

    List<Ticket> findByConcertIdAndIdNumberIn(
            Integer concertId,
            List<String> idNumbers
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM Ticket t WHERE t.concertId = :concertId AND t.seatArea = :seatArea AND (t.idNumber IS NULL OR t.idNumber = '') ORDER BY t.id ASC limit :limit")
    List<Ticket> findEmptyIdNumberWithLock(@Param("concertId") Integer concertId, @Param("seatArea") String seatArea, @Param("limit") int limit);

    List<Ticket> findByUserId(int userId);
}
