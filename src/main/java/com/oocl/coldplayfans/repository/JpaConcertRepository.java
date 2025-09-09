package com.oocl.coldplayfans.repository;
import com.oocl.coldplayfans.dao.Concert;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.stereotype.Repository;
import java.util.Date;

@Repository
public interface JpaConcertRepository extends JpaRepository<Concert, Integer> {
    @Query("SELECT DISTINCT city FROM Concert WHERE isDeleted = false and status != 'cancelled' and status != 'ended'")
    List<String> findDistinctCities();

    @Query("SELECT c FROM Concert c WHERE (:city IS NULL OR LOWER(c.city) = LOWER(:city)) " +
        "AND (:startDate IS NULL OR c.startDate >= :startDate) " +
        "AND (:endDate IS NULL OR c.startDate <= :endDate) " +
        "AND c.status NOT IN ('ended', 'cancelled')")
    List<Concert> findConcertsByDate(String city, Date startDate, Date endDate);
}


