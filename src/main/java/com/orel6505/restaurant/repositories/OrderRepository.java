package com.orel6505.restaurant.repositories;

import com.orel6505.restaurant.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(int userId);

    @Query("SELECT COALESCE(SUM(o.totalPrice), 0.0) FROM Order o WHERE o.userId = :userId")
    Double getTotalSpentByUser(@Param("userId") int userId);

    @Query("SELECT o.id, o.userId, o.totalPrice FROM Order o WHERE o.totalPrice BETWEEN :min AND :max ORDER BY o.totalPrice DESC")
    List<Object[]> getOrdersByPriceRange(@Param("min") double min, @Param("max") double max);

    @Query("SELECT AVG(o.totalPrice) FROM Order o WHERE o.userId = :userId")
    Double getAverageOrderValueByUser(@Param("userId") int userId);

    @Query("SELECT COUNT(o.id), SUM(o.totalPrice) FROM Order o WHERE o.userId = :userId")
    Object[] getUserStatistics(@Param("userId") int userId);
}