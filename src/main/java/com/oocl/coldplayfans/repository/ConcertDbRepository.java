package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.dao.Concert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class ConcertDbRepository implements ConcertRepository {

    @Autowired
    private JpaConcertRepository concertRepository;

    public ConcertDbRepository(JpaConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    @Override
    public List<Concert> getAllConcerts() {
        return concertRepository.findAll();
    }

    @Override
    public Concert getConcertById(Integer id) {
        return concertRepository.findById(id).orElse(null);
    }

    @Override
    public Concert saveConcert(Concert concert) {
        return concertRepository.save(concert);
    }

    @Override
    public void deleteConcert(Integer id) {
        concertRepository.findById(id).ifPresent(concert -> {
            concert.setDeleted(true);
            concertRepository.save(concert);
        });
    }

    @Override
    public Concert updateConcert(Integer id, Concert updatedConcert) {
        return concertRepository.findById(id).map(existingConcert -> {
            existingConcert.setName(updatedConcert.getName());
            existingConcert.setStartDate(updatedConcert.getStartDate());
            existingConcert.setStartTime(updatedConcert.getStartTime());
            existingConcert.setVenue(updatedConcert.getVenue());
            existingConcert.setCity(updatedConcert.getCity());
            existingConcert.setSaleDate(updatedConcert.getSaleDate());
            existingConcert.setSaleTime(updatedConcert.getSaleTime());
            existingConcert.setDescription(updatedConcert.getDescription());
            existingConcert.setSeatMapUrl(updatedConcert.getSeatMapUrl());
            existingConcert.setStatus(updatedConcert.getStatus());
            existingConcert.setImgUrl(updatedConcert.getImgUrl());
            return concertRepository.save(existingConcert);
        }).orElse(null);
    }

}
