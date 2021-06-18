package com.decagon.safariwebstore.service.serviceImplementation;

import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(value="dipson@gmail.com",roles="ADMIN")
public @interface WithMockAdmin {
}
