package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBannerRepository extends JpaRepository<Banner, Integer> {
    // Custom query methods (if needed) can be defined here
}
