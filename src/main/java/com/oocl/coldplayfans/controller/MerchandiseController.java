package com.oocl.coldplayfans.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oocl.coldplayfans.dao.Merchandise;
import com.oocl.coldplayfans.service.MerchandiseService;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Merchandise> getAllMerchandise(@RequestParam(required = false) Integer orderId) {
        if (orderId != null) {
            return merchandiseService.getMerchandiseByOrderId(orderId);
        }
        return merchandiseService.getAllMerchandise();
    }

    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Merchandise getMerchandiseById(@PathVariable Integer id) {
        return merchandiseService.getMerchandiseById(id);
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

    
}
