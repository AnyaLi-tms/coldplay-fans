package com.oocl.coldplayfans.service;

import java.sql.Date;
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
        List<Concert> concerts = concertDbRepository.getAllConcerts();
        if (city != null && !city.isEmpty()) {
            concerts.removeIf(concert -> !concert.getCity().equalsIgnoreCase(city));
        }
        if (startDate != null) {
            concerts.removeIf(concert -> concert.getStartDate().before(startDate));
        }
        if (endDate != null) {
            concerts.removeIf(concert -> concert.getStartDate().after(endDate));
        }
        List<Concert> upcomingConcerts = new java.util.ArrayList<>();
        for (Concert concert : concerts) {
            if (concert.getStatus() != Concert.Status.ended && concert.getStatus() != Concert.Status.cancelled) {
                upcomingConcerts.add(concert);
            }
        }
        upcomingConcerts.sort((java.util.Comparator.comparing(Concert::getStartDate).thenComparing(Concert::getStartTime)));
        return upcomingConcerts;
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
        List<Concert> concerts = concertDbRepository.getAllConcerts();
        java.util.Set<String> citySet = new java.util.HashSet<>();
        for (Concert concert : concerts) {
            citySet.add(concert.getCity());
        }
        List<String> cities = new java.util.ArrayList<>(citySet);
        java.util.Collections.sort(cities);
        return cities;
    }

}
