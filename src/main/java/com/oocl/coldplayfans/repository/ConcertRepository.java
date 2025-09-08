package com.oocl.coldplayfans.repository;

import java.util.List;

import com.oocl.coldplayfans.dao.Concert;


public interface ConcertRepository {
    List<Concert> getAllConcerts();
    Concert getConcertById(Integer id);
    Concert saveConcert(Concert concert);
    void deleteConcert(Integer id);
    Concert updateConcert(Integer id, Concert updatedConcert);
}
