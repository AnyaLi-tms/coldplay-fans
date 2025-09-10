package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.model.Merchandise;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface JpaMerchandiseRepository extends JpaRepository<Merchandise, Integer> {
    @Query("SELECT m FROM Merchandise m WHERE m.isDeleted = false" + " AND m.orderId = :orderId")
    List<Merchandise> findByOrderId(Integer orderId);

    @Query("SELECT m FROM Merchandise m WHERE m.status = 'instock' AND m.isDeleted = false AND m.name = :name")
    List<Merchandise> findInStockMerchandises(String name);

    @Query("SELECT m FROM Merchandise m WHERE m.userId = :userId AND m.isDeleted = false")
    List<Merchandise> findByUserId(Integer userId);

}
