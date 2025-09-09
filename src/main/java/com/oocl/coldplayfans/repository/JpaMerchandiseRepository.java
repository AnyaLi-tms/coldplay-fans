package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.dao.Merchandise;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface JpaMerchandiseRepository extends JpaRepository<Merchandise, Integer> {
    @Query("SELECT m FROM Merchandise m WHERE m.isDeleted = false" + " AND m.order.id = :orderId")
    List<Merchandise> findByOrderId(Integer orderId);
}
