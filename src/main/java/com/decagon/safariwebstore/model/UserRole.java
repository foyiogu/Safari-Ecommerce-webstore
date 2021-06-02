package com.decagon.safariwebstore.model;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long accountId;
    @Column(name = "role_id")
    private Long roleId;
}
