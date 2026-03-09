package com.bank.credit.entity;

import jakarta.persistence.*;


@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="credit_id")
    private Long userId;

    @Column(name="customer_id")
    private Long customerId;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.Usuario;

    @OneToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable=false)
    private Customer customer;

    public User(Long userId, Long customerId, String email, String password, UserRole role) {
        this.userId = userId;
        this.customerId = customerId;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public enum UserRole {
        Usuario,
        Administrador,
    }
}
