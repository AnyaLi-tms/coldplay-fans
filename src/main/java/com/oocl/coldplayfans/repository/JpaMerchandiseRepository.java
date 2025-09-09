package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.dao.Merchandise;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface JpaMerchandiseRepository extends JpaRepository<Merchandise, Integer> {

}
