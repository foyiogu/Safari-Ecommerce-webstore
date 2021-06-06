package com.decagon.safariwebstore.repository;

import com.decagon.safariwebstore.model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        categoryRepositoryUnderTest.deleteAll();
    }

    @Test
    void shouldFindCategoryByName() {
        // given
        Category clothes = new Category("clothes");
        categoryRepositoryUnderTest.save(clothes);
        Category shoes = new Category("shoes");
        categoryRepositoryUnderTest.save(shoes);
        Category accessories = new Category("accessories");
        categoryRepositoryUnderTest.save(accessories);

        // when
        Optional<Category> clothesCategory = categoryRepositoryUnderTest.findByName("clothes");
        Optional<Category> shoesCategory = categoryRepositoryUnderTest.findByName("shoes");
        Optional<Category> accessoriesCategory = categoryRepositoryUnderTest.findByName("accessories");

        // then
        assertAll(
                () -> assertTrue(clothesCategory.isPresent()),
                () -> assertTrue(shoesCategory.isPresent()),
                () -> assertTrue(accessoriesCategory.isPresent())
        );
    }
}