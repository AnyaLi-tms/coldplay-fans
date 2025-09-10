package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.model.Merchandise;

import jakarta.persistence.LockModeType;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface JpaMerchandiseRepository extends JpaRepository<Merchandise, Integer> {
    @Query("SELECT m FROM Merchandise m WHERE m.isDeleted = false" + " AND m.orderId = :orderId")
    List<Merchandise> findByOrderId(Integer orderId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT m FROM Merchandise m WHERE m.status = 'instock' AND m.isDeleted = false AND m.type = :type")
    List<Merchandise> findInStockMerchandises(String type);

    @Query("SELECT m FROM Merchandise m WHERE m.userId = :userId AND m.isDeleted = false")
    List<Merchandise> findByUserId(Integer userId);

    @Query("SELECT DISTINCT m.name FROM Merchandise m WHERE m.isDeleted = false AND m.status = 'instock'")
    List<String> findAllInStock();


}
