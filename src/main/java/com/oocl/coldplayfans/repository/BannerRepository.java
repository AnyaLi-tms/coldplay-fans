package com.oocl.coldplayfans.repository;
import java.util.List;

import com.oocl.coldplayfans.dao.Banner;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository {
    // Define custom query methods if needed
    List<Banner> getAllBanners();
    Banner getBannerById(Integer id);
    Banner saveBanner(Banner banner);
    void deleteBanner(Integer id);
    Banner updateBanner(Integer id, Banner updatedBanner);
}

