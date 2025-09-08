package com.oocl.coldplayfans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oocl.coldplayfans.dao.Concert;
import com.oocl.coldplayfans.repository.ConcertDbRepository;

@Service
public class ConcertService {

    @Autowired
    private ConcertDbRepository concertDbRepository;

    public ConcertService(ConcertDbRepository concertDbRepository) {
        this.concertDbRepository = concertDbRepository;
    }

    public List<Concert> getAllConcerts() {
        return concertDbRepository.getAllConcerts();
    }

    public Concert getConcertById(Integer id) {
        return concertDbRepository.getConcertById(id);
    }

    public Concert createConcert(Concert concert) {
        return concertDbRepository.saveConcert(concert);
    }

    public void deleteConcert(Integer id) {
        concertDbRepository.deleteConcert(id);
    }

    public Concert updateConcert(Integer id, Concert updatedConcert) {
        return concertDbRepository.updateConcert(id, updatedConcert);
    }

}
