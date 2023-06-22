package com.example.hyperlocalecommerce.hyperlocalecommerce.repositories;

import com.example.hyperlocalecommerce.hyperlocalecommerce.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>
{
}
