package com.oocl.coldplayfans.repository;
import com.oocl.coldplayfans.model.Order;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, Integer> {

}
