package com.example.hyperlocalecommerce.hyperlocalecommerce.repositories;


import com.example.hyperlocalecommerce.hyperlocalecommerce.entities.Cart;
import com.example.hyperlocalecommerce.hyperlocalecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {


    Optional<Cart> findByUser(User user);

}
