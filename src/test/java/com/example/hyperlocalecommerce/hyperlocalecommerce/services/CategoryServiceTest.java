package com.example.hyperlocalecommerce.hyperlocalecommerce.services;

import com.example.hyperlocalecommerce.hyperlocalecommerce.dtos.CategoryDto;
import com.example.hyperlocalecommerce.hyperlocalecommerce.dtos.PageableResponse;
import com.example.hyperlocalecommerce.hyperlocalecommerce.entities.Category;
import com.example.hyperlocalecommerce.hyperlocalecommerce.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CategoryServiceTest {

    //TODO:1 complete test cases for Category Service


    @MockBean
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    Category category;

    @Autowired
    private CategoryService categoryService;



    @BeforeEach
    private void init(){
        category = Category.builder().categoryId("123")
                .title("Refrigerator")
                .description("this categories contains Refrigerator")
                .coverImage("def.png")
                .build();
    }

    @Test
    public void createCategoryTest(){
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(category);
        CategoryDto category1 = categoryService.create(modelMapper.map(category, CategoryDto.class));
        Assertions.assertNotNull(category1);
        Assertions.assertEquals("Refrigerator", category1.getTitle());

    }

    @Test
    public void updateCategoryTest() {
        String categoryId = "123";
        CategoryDto categoryDto = CategoryDto.builder()
                .title("Smart Tv")
                .description("this categories contains smarttv")
                .coverImage("def.png")
                .build();

        Mockito.when(categoryRepository.findById(Mockito.anyString())).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(category);

        CategoryDto updatedCategory = categoryService.update(categoryDto, categoryId);
        System.out.println(updatedCategory.getTitle());
        System.out.println(updatedCategory.getDescription());
        Assertions.assertNotNull(categoryDto);
        Assertions.assertEquals(categoryDto.getTitle(), updatedCategory.getTitle(), " Category Title is not validated !!");
        //multiple assertion are valid..
    }


    @Test
    public void getAllCategoryTest() {
      Category  category1 = Category.builder()
                .title("watches")
                .description("this categories contains watches")
                .coverImage("def.png")
                .build();
        Category category2 = Category.builder()
                .title("smarttv")
                .description("this categories contains SmartTv")
                .coverImage("def.png")
                .build();
        List<Category> categoryList = Arrays.asList(category, category1, category2);
        Page<Category> page = new PageImpl<>(categoryList);
        Mockito.when(categoryRepository.findAll((Pageable) Mockito.any())).thenReturn(page);
        PageableResponse<CategoryDto> allCategories = categoryService.getAll(1, 2, "title", "asc");
        Assertions.assertEquals(3, allCategories.getContent().size());


    }

    @Test
    public void getProductByIdTest() {

        String categoryId = "abc";
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        //actual call of service method

        CategoryDto categoryDto = categoryService.get(categoryId);

        Assertions.assertNotNull(categoryDto);
        Assertions.assertEquals(category.getTitle(), categoryDto.getTitle(), "title not matched !!");


    }

}
