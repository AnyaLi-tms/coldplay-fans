package com.oocl.coldplayfans.controller;

import com.oocl.coldplayfans.dao.Ticket;
import com.oocl.coldplayfans.dto.TicketPricesReponse;
import com.oocl.coldplayfans.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ticket")
@CrossOrigin
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/prices/{concertId}")
    public TicketPricesReponse getPriceListByConcert(@PathVariable Integer concertId){
        return ticketService.getPriceListByConcert(concertId);
    }
}
