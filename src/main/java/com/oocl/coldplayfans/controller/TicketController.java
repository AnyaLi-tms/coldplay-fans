package com.oocl.coldplayfans.controller;

import com.oocl.coldplayfans.model.Ticket;
import com.oocl.coldplayfans.dto.TicketPricesReponse;
import com.oocl.coldplayfans.dto.UserTicketOrderReponse;
import com.oocl.coldplayfans.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ticket")
@CrossOrigin
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/prices/{concertId}")
    public TicketPricesReponse getPriceListByConcert(@PathVariable Integer concertId) {
        return ticketService.getPriceListByConcert(concertId);
    }

    @PutMapping("/buyTicket/{concertId}")
    public ResponseEntity<?> buyTickets(@PathVariable Integer concertId, HttpServletRequest request, @RequestBody Map<String, Object> map) {
        try {
            List<String> idNums = new ArrayList<>();
            Object idNumsObj = map.get("idNums");
            if (idNumsObj instanceof List<?> tempList) {
                for (Object item : tempList) {
                    idNums.add((String) item);
                }
            }
            String seatArea = (String) map.get("seatArea");
            if (seatArea == null || seatArea.trim().isEmpty()) {
                Map<String, String> errMap = new HashMap<>();
                errMap.put("msg", "seatArea不能为空");
                errMap.put("status", "false");
                return ResponseEntity.badRequest().body(errMap);
            }
            Integer userId = Integer.parseInt((String) request.getAttribute("userId"));
            List<Ticket> existingTickets = ticketService.findTicketByIdNumsAndConcertId(concertId, idNums);
            if (!existingTickets.isEmpty()) {
                Map<String, String> errMap = new HashMap<>();
                errMap.put("msg", "对不起，您所选的观演人已购买过同一时间的演出");
                errMap.put("status", "false");
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(errMap);
            }
            List<Ticket> tickets = ticketService.buyTickets(userId, seatArea, idNums, concertId);
            Map<String, String> successMap = new HashMap<>();
            successMap.put("msg", "购票成功");
            successMap.put("tickets", tickets.toString());
            successMap.put("status", "true");
            return ResponseEntity.ok(successMap);
        } catch (Exception e) {
            Map<String, String> errMap = new HashMap<>();
            errMap.put("msg", "购票失败：" + e.getMessage());
            errMap.put("status", "false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errMap);
        }
    }

    @GetMapping("/myTicket")
    public List<UserTicketOrderReponse> loadTicketOrders(HttpServletRequest request){
        return ticketService.loadTicketOrders(Integer.parseInt((String) request.getAttribute("userId")));
    }

}
