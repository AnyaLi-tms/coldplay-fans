package com.oocl.coldplayfans.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oocl.coldplayfans.repository.BannerDbRepository;
import com.oocl.coldplayfans.dao.Banner;
import java.util.List;



@Service
public class BannerService {

    @Autowired
    private BannerDbRepository bannerDbRepository;

    public BannerService(BannerDbRepository bannerDbRepository) {
        this.bannerDbRepository = bannerDbRepository;
    }

    public List<Banner> getAllBanners() {
        return bannerDbRepository.getAllBanners();
    }
    public Banner getBannerById(Integer id) {
        return bannerDbRepository.getBannerById(id);
    }
    public Banner createBanner(Banner banner) {
        return bannerDbRepository.saveBanner(banner);
    }
    public void deleteBanner(Integer id) {
        bannerDbRepository.deleteBanner(id);
    }
    public Banner updateBanner(Integer id, Banner updatedBanner) {
        return bannerDbRepository.updateBanner(id, updatedBanner);
    }


}


