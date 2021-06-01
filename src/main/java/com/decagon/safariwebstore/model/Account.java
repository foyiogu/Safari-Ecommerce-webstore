package com.decagon.safariwebstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account extends AutoDate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private  String email;
    private String gender;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private String password;
    @Column(name = "password_reset_token", columnDefinition = "TEXT")
    private String passwordResetToken;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "password_reset_expire_date")
    private Date passwordResetExpireDate;
    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    @ManyToMany
    @JoinTable(name = "favorite_products",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> favouriteProducts;


}
