package com.example.demo.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.validator.annotation.ValidUsername;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    @NotBlank(message = "Không được để trống")
    @Size(max = 50, message = "Không được lớn hơn 50 ký tự")
    @ValidUsername
    private String username;

    @Column(name = "password", length = 250, nullable = false)
    @NotBlank(message = "Không được để trống")
    private String password;

    @Column(name = "email", length = 50)
    @Size(max = 50, message = "Không được lớn hơn 50 ký tự")
    @Email(message = "Không đúng định dạng")
    private String email;

    @Column(name = "name", length = 50, nullable = false)
    @Size(max = 50, message = "Không được lớn hơn 50 ký tự")
    @NotBlank(message = "Không được để trống")
    private String name;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

}
