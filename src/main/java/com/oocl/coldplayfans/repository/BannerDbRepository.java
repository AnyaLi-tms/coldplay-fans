
package com.oocl.coldplayfans.repository;
import com.oocl.coldplayfans.dao.Banner;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;


@Repository
public class BannerDbRepository implements BannerRepository {

    JpaBannerRepository bannerRepository;

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
        bannerRepository.deleteById(id);
    }
    @Override
    public Banner updateBanner(Integer id, Banner updatedBanner) {
        Optional<Banner> optionalBanner = bannerRepository.findById(id);
        if (optionalBanner.isPresent()) {
            Banner existingBanner = optionalBanner.get();
            existingBanner.setName(updatedBanner.getName());
            existingBanner.setImgUrl(updatedBanner.getImgUrl());
            existingBanner.setLink(updatedBanner.getLink());
            existingBanner.setIsDeleted(updatedBanner.getIsDeleted());
            existingBanner.setStatus(updatedBanner.getStatus());
            return bannerRepository.save(existingBanner);
        } else {
            return null; // Or throw an exception if preferred
        }
    }
    
}
