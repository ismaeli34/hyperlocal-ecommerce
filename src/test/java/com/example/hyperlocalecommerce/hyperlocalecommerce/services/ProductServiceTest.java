package com.example.hyperlocalecommerce.hyperlocalecommerce.services;

import com.example.hyperlocalecommerce.hyperlocalecommerce.dtos.PageableResponse;
import com.example.hyperlocalecommerce.hyperlocalecommerce.dtos.ProductDto;
import com.example.hyperlocalecommerce.hyperlocalecommerce.entities.Product;
import com.example.hyperlocalecommerce.hyperlocalecommerce.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class ProductServiceTest {
    //TODO:2 complete test cases for Product Service

    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    Product product;

    @Autowired
    private ProductService productService;

    @BeforeEach
    private void init(){
         product = Product.builder().productId("123")
                .title("Samsung")
                .description("smarttv")
                .price(900)
                .quantity(5)
                .live(true)
                .stock(true)
                .discountedPrice(800)
                .build();
    }

    @Test
    public void createProductTest(){
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);
        ProductDto product1 = productService.create(modelMapper.map(product, ProductDto.class));
        Assertions.assertNotNull(product1);
        Assertions.assertEquals("Samsung", product1.getTitle());

    }

    @Test
    public void updateProductTest() {
        String productId = "123";
        ProductDto productDto = ProductDto.builder()
                .title("Nokia Pro")
                .description("Windows phone")
                .price(900)
                .quantity(5)
                .live(false)
                .stock(true)
                .discountedPrice(800)
                .build();

        Mockito.when(productRepository.findById(Mockito.anyString())).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);

        ProductDto updatedProduct = productService.update(productDto, productId);
        System.out.println(updatedProduct.getTitle());
        System.out.println(updatedProduct.getDescription());
        Assertions.assertNotNull(productDto);
        Assertions.assertEquals(productDto.getTitle(), updatedProduct.getTitle(), " Product Title is not validated !!");
        //multiple assertion are valid..
    }


    @Test
    public void getAllProductTest() {
        Product product1 = Product.builder()
                .title("Nokia Pro")
                .description("Windows phone")
                .price(900)
                .quantity(5)
                .live(false)
                .stock(true)
                .discountedPrice(800)
                .build();
        Product product2 = Product.builder()
                .title("Windows Pro")
                .description("Windows phone")
                .price(800)
                .quantity(10)
                .live(false)
                .stock(true)
                .discountedPrice(700)
                .build();
        List<Product> productList = Arrays.asList(product, product1, product2);
        Page<Product> page = new PageImpl<>(productList);
        Mockito.when(productRepository.findAll((Pageable) Mockito.any())).thenReturn(page);
        PageableResponse<ProductDto> allProducts = productService.getAll(1, 2, "title", "asc");
        Assertions.assertEquals(3, allProducts.getContent().size());


    }

    @Test
    public void getProductByIdTest() {

        String productId = "abc";
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        //actual call of service method

        ProductDto productDto = productService.get(productId);

        Assertions.assertNotNull(productDto);
        Assertions.assertEquals(product.getTitle(), productDto.getTitle(), "title not matched !!");


    }


}
