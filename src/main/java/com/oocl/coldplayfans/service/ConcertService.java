package com.oocl.coldplayfans.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    public List<Concert> getAllConcerts(Date startDate, Date endDate, String city) {
        updateStatus();
        return concertDbRepository.getConcertsByDate(city, startDate, endDate);
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

    public List<String> getAllCities() {
        return concertDbRepository.getAllCities();
    }

    public List<Concert> updateStatus() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        List<Concert> concerts = concertDbRepository.getAllConcerts();
        for (Concert concert : concerts) {
            if (concert.getStartDate().toLocalDate().isBefore(now.toLocalDate())) {
                concert.setStatus(Concert.Status.ended);
                concertDbRepository.updateConcert(concert.getId(), concert);
            }
            if (concert.getStartDate().toLocalDate().isAfter(now.toLocalDate())) {
                if (java.time.temporal.ChronoUnit.DAYS.between(now.toLocalDate(), concert.getStartDate().toLocalDate()) <= 30 && concert.getStatus() != Concert.Status.sold && concert.getStatus() != Concert.Status.cancelled) {
                    concert.setStatus(Concert.Status.available);
                    concertDbRepository.updateConcert(concert.getId(), concert);
                } else {
                    concert.setStatus(Concert.Status.unreleased);
                    concertDbRepository.updateConcert(concert.getId(), concert);
                }
            }
        }

        return concerts;
    }

}
