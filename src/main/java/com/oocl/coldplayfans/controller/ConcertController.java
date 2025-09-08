package com.oocl.coldplayfans.controller;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oocl.coldplayfans.dao.Concert;
import com.oocl.coldplayfans.service.ConcertService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/concerts")
@CrossOrigin
public class ConcertController {

    @Autowired
    private ConcertService concertService;

    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @GetMapping
    public List<Concert> getAllConcerts(@RequestParam(required = false) Date startDate, @RequestParam(required = false) Date endDate, @RequestParam(required = false) String city) {
        return concertService.getAllConcerts(startDate, endDate, city);
    }

    @GetMapping("/cities")
    public List<String> getAllCities() {
        return concertService.getAllCities();
    }
    

    @GetMapping("/{id}")
    public Concert getConcertById(@PathVariable Integer id) {
        return concertService.getConcertById(id);
    }

    @PostMapping
    public Concert createConcert(@RequestBody Concert concert) {
        return concertService.createConcert(concert);
    }

    @DeleteMapping("/{id}")
    public void deleteConcert(@PathVariable Integer id) {
        concertService.deleteConcert(id);
    }

    @PutMapping("/{id}")
    public Concert updateConcert(@PathVariable Integer id, @RequestBody Concert updatedConcert) {
        return concertService.updateConcert(id, updatedConcert);
    }

}
