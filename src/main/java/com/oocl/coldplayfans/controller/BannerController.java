package com.oocl.coldplayfans.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oocl.coldplayfans.dao.Banner;
import com.oocl.coldplayfans.service.BannerService;
 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/banners")
@CrossOrigin
public class BannerController {

    @Autowired
    private BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }


    @GetMapping
    public List<Banner> getBanners() {
        return bannerService.getAllBanners();
    }

    @GetMapping("/{id}")
    public Banner getBannerById(@PathVariable Integer id) {
        return bannerService.getBannerById(id);
    }

    @PostMapping
    public Banner createBanner(@RequestBody Banner banner) {
        return bannerService.createBanner(banner);
    }

    @PutMapping("/{id}")
    public Banner updateBanner(@PathVariable Integer id, @RequestBody Banner updatedBanner) {
        return bannerService.updateBanner(id, updatedBanner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBanner(@PathVariable Integer id) {
        bannerService.deleteBanner(id);
        return ResponseEntity.noContent().build();
    }
    

}

