package com.example.hyperlocalecommerce.hyperlocalecommerce.repositories;

import com.example.hyperlocalecommerce.hyperlocalecommerce.entities.Order;
import com.example.hyperlocalecommerce.hyperlocalecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByUser(User user);

}
