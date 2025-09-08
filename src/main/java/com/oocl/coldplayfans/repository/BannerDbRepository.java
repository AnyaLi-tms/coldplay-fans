package com.oocl.coldplayfans.repository;
import com.oocl.coldplayfans.dao.Banner;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class BannerDbRepository implements BannerRepository {

    @Autowired
    private JpaBannerRepository bannerRepository;

    public BannerDbRepository(JpaBannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }
    @Override
    public List<Banner> getAllBanners() {
        return bannerRepository.findAll();
    }
    @Override
    public Banner getBannerById(Integer id) {
        return bannerRepository.findById(id).orElse(null);
    }
    @Override
    public Banner saveBanner(Banner banner) {
        return bannerRepository.save(banner);
    }
    @Override
    public void deleteBanner(Integer id) {
        bannerRepository.findById(id).ifPresent(banner -> {
            banner.setIsDeleted(true);
            banner.setStatus(false);
            bannerRepository.save(banner);
        });
    }
    @Override
    public Banner updateBanner(Integer id, Banner updatedBanner) {
        return bannerRepository.findById(id).map(existingBanner -> {
            existingBanner.setName(updatedBanner.getName());
            existingBanner.setImgUrl(updatedBanner.getImgUrl());
            existingBanner.setLink(updatedBanner.getLink());
            existingBanner.setStatus(updatedBanner.getStatus());
            existingBanner.setIsDeleted(updatedBanner.getIsDeleted());
            return bannerRepository.save(existingBanner);
        }).orElse(null);
    }
    
}
