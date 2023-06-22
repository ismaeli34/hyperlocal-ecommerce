package com.example.hyperlocalecommerce.hyperlocalecommerce.repositories;


import com.example.hyperlocalecommerce.hyperlocalecommerce.entities.Category;
import com.example.hyperlocalecommerce.hyperlocalecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    //search
    Page<Product> findByTitleContaining(String subTitle, Pageable pageable);

    Page<Product> findByLiveTrue(Pageable pageable);

    Page<Product> findByCategory(Category category, Pageable pageable);
    //other methods
    //custom finder methods
    //query methods

}
