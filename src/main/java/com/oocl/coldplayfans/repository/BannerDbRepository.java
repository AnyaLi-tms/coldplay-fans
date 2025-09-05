
package com.oocl.coldplayfans.repository;
import com.oocl.coldplayfans.dao.Banner;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;


@Repository
public class BannerDbRepository {

    private final BannerRepository bannerRepository;

    public BannerDbRepository(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }
    public List<Banner> getAllBanners() {
        return bannerRepository.findAll();
    }
    public Optional<Banner> getBannerById(Integer id) {
        return bannerRepository.findById(id);
    }
    public Banner saveBanner(Banner banner) {
        return bannerRepository.save(banner);
    }
    public void deleteBanner(Integer id) {
        bannerRepository.deleteById(id);
    }
    public Banner updateBanner(Integer id, Banner updatedBanner) {
        return bannerRepository.findById(id)
                .map(banner -> {
                    banner.setName(updatedBanner.getName());
                    banner.setUrl(updatedBanner.getUrl());
                    banner.setStatus(updatedBanner.getStatus());
                    return bannerRepository.save(banner);
                })
                .orElseThrow(() -> new RuntimeException("Banner not found with id " + id));
    }
}
