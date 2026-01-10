package com.orel6505.restaurant.services;

import com.orel6505.restaurant.repositories.OrderRepository;
import com.orel6505.restaurant.repositories.OrderDetailRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class providing query functions for the restaurant application
 * Delegates to repositories for JPQL execution
 */
@Service
public class JpqlQueryService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public JpqlQueryService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    /**
     * Get total amount spent by a specific user
     * @param userId the ID of the user
     * @return total amount spent
     */
    public Double getTotalSpentByUser(int userId) {
        return orderRepository.getTotalSpentByUser(userId);
    }

    /**
     * Get the most popular dishes (top N by order count)
     * @param limit number of top dishes to return
     * @return list of dish IDs with their order counts
     */
    public List<Object[]> getTopOrderedDishes(int limit) {
        List<Object[]> results = orderDetailRepository.getTopOrderedDishes();
        return results.size() > limit ? results.subList(0, limit) : results;
    }

    /**
     * Find all orders with total amount within a price range
     * @param minPrice minimum order amount
     * @param maxPrice maximum order amount
     * @return list of orders within the price range
     */
    public List<Object[]> getOrdersByPriceRange(double minPrice, double maxPrice) {
        return orderRepository.getOrdersByPriceRange(minPrice, maxPrice);
    }

    /**
     * Calculate average order value for a user
     * @param userId the ID of the user
     * @return average order value
     */
    public Double getAverageOrderValueByUser(int userId) {
        Double result = orderRepository.getAverageOrderValueByUser(userId);
        return result != null ? result : 0.0;
    }

    /**
     * Get specific user order statistics (count and total spent)
     * @param userId the ID of the user
     * @return array with orderCount and totalSpent
     */
    public Object[] getUserStatistics(int userId) {
        return orderRepository.getUserStatistics(userId);
    }
}
