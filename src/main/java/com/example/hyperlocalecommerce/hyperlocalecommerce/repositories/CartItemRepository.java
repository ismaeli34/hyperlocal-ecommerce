package com.example.hyperlocalecommerce.hyperlocalecommerce.repositories;

import com.example.hyperlocalecommerce.hyperlocalecommerce.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
}
