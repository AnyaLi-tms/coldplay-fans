package com.oocl.coldplayfans.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.oocl.coldplayfans.dao.Banner;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {

}

