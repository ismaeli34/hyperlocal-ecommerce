package com.example.hyperlocalecommerce.hyperlocalecommerce.repositories;

import com.example.hyperlocalecommerce.hyperlocalecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String>
{
}
