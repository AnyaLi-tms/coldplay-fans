package com.oocl.coldplayfans.repository;
import com.oocl.coldplayfans.dao.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaConcertRepository extends JpaRepository<Concert, Integer> {
    
}


