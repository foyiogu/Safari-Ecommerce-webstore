package com.decagon.safariwebstore.service.serviceImplementation;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.decagon.safariwebstore.exceptions.BadRequestException;
import com.decagon.safariwebstore.model.ProductPage;
import com.decagon.safariwebstore.repository.ProductRepository;
import com.decagon.safariwebstore.repository.RoleRepository;
import com.decagon.safariwebstore.service.UserService;
import com.decagon.safariwebstore.utils.mailService.MailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ModelMapper.class, AdminServiceImplementation.class, BCryptPasswordEncoder.class})
@ExtendWith(SpringExtension.class)
public class AdminServiceImplementationTest {
    @Autowired
    private AdminServiceImplementation adminServiceImplementation;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private MailService mailService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllProducts() {
        assertThrows(BadRequestException.class, () -> this.adminServiceImplementation.getAllProducts(new ProductPage()));
    }

    @Test
    public void testGetSingleProduct() {
        assertThrows(BadRequestException.class, () -> this.adminServiceImplementation.getSingleProduct(123L));
    }
}

