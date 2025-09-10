package com.oocl.coldplayfans.controller;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oocl.coldplayfans.model.Concert;
import com.oocl.coldplayfans.service.ConcertService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;



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
    @ResponseStatus(HttpStatus.OK)
    public List<Concert> getAllConcerts(@RequestParam(required = false) Date startDate, @RequestParam(required = false) Date endDate, @RequestParam(required = false) String city) {
        return concertService.getAllConcerts(startDate, endDate, city);
    }

    @GetMapping("/cities")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllCities() {
        return concertService.getAllCities();
    }
    

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Concert getConcertById(@PathVariable Integer id) {
        return concertService.getConcertById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Concert createConcert(@RequestBody Concert concert) {
        return concertService.createConcert(concert);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConcert(@PathVariable Integer id) {
        concertService.deleteConcert(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Concert updateConcert(@PathVariable Integer id, @RequestBody Concert updatedConcert) {
        return concertService.updateConcert(id, updatedConcert);
    }

}
