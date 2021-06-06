package com.decagon.safariwebstore.repository;

import com.decagon.safariwebstore.model.Category;
import com.decagon.safariwebstore.model.SubCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SubCategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepositoryUnderTest;

    @Autowired
    private SubCategoryRepository subCategoryRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        subCategoryRepositoryUnderTest.deleteAll();
        categoryRepositoryUnderTest.deleteAll();
    }

    @Test
    void findByNameAndCategory() {
        // given
        Category clothes = new Category("clothes");
        categoryRepositoryUnderTest.save(clothes);
        Category shoes = new Category("shoes");
        categoryRepositoryUnderTest.save(shoes);
        Category accessories = new Category("accessories");
        categoryRepositoryUnderTest.save(accessories);

        SubCategory dresses = new SubCategory("dresses", clothes);
        subCategoryRepositoryUnderTest.save(dresses);
        SubCategory denim = new SubCategory("denim", clothes);
        subCategoryRepositoryUnderTest.save(denim);
        SubCategory flats = new SubCategory("flats", shoes);
        subCategoryRepositoryUnderTest.save(flats);
        SubCategory watches = new SubCategory("watches", accessories);
        subCategoryRepositoryUnderTest.save(watches);

        // when
        Optional<SubCategory> clothesSubcategory = subCategoryRepositoryUnderTest
                .findByNameAndCategory("dresses", clothes);
        Optional<SubCategory> clothesSubcategory1 = subCategoryRepositoryUnderTest
                .findByNameAndCategory("denim", clothes);
        Optional<SubCategory> shoesSubcategory = subCategoryRepositoryUnderTest
                .findByNameAndCategory("flats", shoes);
        Optional<SubCategory> accessoriesSubcategory = subCategoryRepositoryUnderTest
                .findByNameAndCategory("watches", accessories);

        // then
        assertAll(
                () -> assertTrue(clothesSubcategory.isPresent()),
                () -> assertTrue(clothesSubcategory1.isPresent()),
                () -> assertTrue(shoesSubcategory.isPresent()),
                () -> assertTrue(accessoriesSubcategory.isPresent())
        );

    }
}