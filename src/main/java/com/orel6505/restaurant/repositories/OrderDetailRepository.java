package com.orel6505.restaurant.repositories;

import com.orel6505.restaurant.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findByOrderId(int orderId);
    List<OrderDetail> findByDishId(int dishId);

    @Query("SELECT od.dishId, COUNT(od.id) FROM OrderDetail od GROUP BY od.dishId ORDER BY COUNT(od.id) DESC")
    List<Object[]> getTopOrderedDishes();
}