package com.oocl.coldplayfans.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oocl.coldplayfans.model.Merchandise;
import com.oocl.coldplayfans.dto.UserMerchandiseResponse;
import com.oocl.coldplayfans.service.MerchandiseService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/merchandises")
@CrossOrigin
public class MerchandiseController {
    @Autowired
    private MerchandiseService merchandiseService;

    public MerchandiseController(MerchandiseService merchandiseService) {
        this.merchandiseService = merchandiseService;
    }

    @GetMapping("/distinct")
    @ResponseStatus(HttpStatus.OK)
    public List<Merchandise> getAllDistinctMerchandise() {
        return merchandiseService.getAllDistinctMerchandise();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Merchandise> getAllMerchandise(@RequestParam(required = false) Integer orderId) {
        if (orderId != null) {
            return merchandiseService.getMerchandiseByOrderId(orderId);
        }
        return merchandiseService.getAllMerchandise();
    }

    @GetMapping("/count")
    public Map<String, Integer> getMerchandiseCount() {
        return merchandiseService.getMerchandiseCountMap();
    }
    

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Merchandise getMerchandiseById(@PathVariable Integer id) {
        return merchandiseService.getMerchandiseById(id);
    }

    @GetMapping("/order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Merchandise> getMerchandiseByOrderId(@PathVariable Integer id) {
        return merchandiseService.getMerchandiseByOrderId(id);
    }

    @GetMapping("/instock")
    public List<Merchandise> getInStockMerchandise(@RequestBody Map<String, Object> map) {
        Integer quantity = (Integer) map.get("quantity");
        String type = (String) map.get("type");
        List<Merchandise> inStockMerchandises = merchandiseService.getInStockMerchandises(type);
        if (inStockMerchandises.size() < quantity) {
            throw new RuntimeException("库存不足，请重试");
        }
        return inStockMerchandises;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMerchandise(@PathVariable Integer id) {
        merchandiseService.deleteMerchandise(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Merchandise createMerchandise(@RequestBody Merchandise merchandise) {
        return merchandiseService.createMerchandise(merchandise);
    }

    @PutMapping("/{id}")
    public Merchandise updateMerchandise(@PathVariable Integer id, @RequestBody Merchandise merchandise) {
        return merchandiseService.updateMerchandise(id, merchandise);
    }


    @PutMapping("/buyMerchandise")
    public ResponseEntity<?> buyMerchandise(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        try {
            Integer userId = Integer.parseInt((String) request.getAttribute("userId"));
            Integer quantity = (Integer) map.get("quantity");
            String type = (String) map.get("type");
            String address = (String) map.get("address");
            List<Merchandise> merchandises = merchandiseService.buyMerchandises(userId, quantity, type, address);
            Map<String, String> successMap = new HashMap<>();
            successMap.put("msg", "购买周边成功");
            successMap.put("merchandises", merchandises.toString());
            successMap.put("status", "true");
            return ResponseEntity.ok(successMap);
        } catch (Exception e) {
            Map<String, String> errMap = new HashMap<>();
            errMap.put("msg", "购买周边失败：" + e.getMessage());
            errMap.put("status", "false");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errMap);
        }
    }

    @GetMapping("/myMerchandise")
    public List<UserMerchandiseResponse> loadMerchandiseOrders(HttpServletRequest request) {
        return merchandiseService.loadMerchandiseOrders(Integer.parseInt((String) request.getAttribute("userId")));
    }


}
