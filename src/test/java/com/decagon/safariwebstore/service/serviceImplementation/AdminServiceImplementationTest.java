package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.exceptions.ResourceNotFoundException;
import com.decagon.safariwebstore.model.*;
import com.decagon.safariwebstore.repository.ProductRepository;
import com.decagon.safariwebstore.utils.MethodUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
@Slf4j
@ExtendWith(MockitoExtension.class)
public class AdminServiceImplementationTest {

    private ProductPage productPage;
    @Mock private ProductRepository productRepository;
    @InjectMocks
    private AdminServiceImplementation adminServiceUnderTest;
    @BeforeEach
    void setUp() {
        productPage = new ProductPage();
    }
    @Test
    void getAllProductsByAdmin() {
        Pageable pageable = MethodUtils.getPageable(productPage);
        // when
        adminServiceUnderTest.getAllProduct(productPage);

        // then
        verify(productRepository).findAll(pageable);
    }
    @Test
    void willThrowExceptionWhenProductNotFound() {

        assertThatThrownBy(() -> adminServiceUnderTest.fetchSingleProduct(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("This product is not available");
    }
}

