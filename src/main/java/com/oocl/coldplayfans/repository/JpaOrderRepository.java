package com.oocl.coldplayfans.repository;
import com.oocl.coldplayfans.dao.Order;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.itemType =:itemType AND o.userId =:userId AND o.isDeleted = false")
    List<Order> findByItemType(String itemType, Integer userId);
}
