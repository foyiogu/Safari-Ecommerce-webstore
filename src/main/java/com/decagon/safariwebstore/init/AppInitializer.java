package com.decagon.safariwebstore.init;
import com.decagon.safariwebstore.model.*;
import com.decagon.safariwebstore.repository.CategoryRepository;
import com.decagon.safariwebstore.repository.RoleRepository;
import com.decagon.safariwebstore.repository.SubCategoryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class AppInitializer implements ApplicationRunner {
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    @Override
    public void run(ApplicationArguments args) {
        Role role;
        if (roleRepository.findByName(ERole.ADMIN).isEmpty()) {
            role = new Role();
            role.setName(ERole.ADMIN);
            roleRepository.save(role);
        }
        if (roleRepository.findByName(ERole.USER).isEmpty()) {
            role = new Role();
            role.setName(ERole.USER);
            roleRepository.save(role);
        }
    }
}