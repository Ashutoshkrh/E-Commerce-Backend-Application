package com.globazaar.userservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // Lombok annotation to create getters, setters, toString, etc. automatically
@Entity // Tells JPA that this class is a table in the database
@Table(name = "users") // Specifies the table name
public class User {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the ID to be auto-incremented
    private Long id;
    @Size(min = 6, message = "Username should have atleast 6 characters")
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    @Size(min = 6, message = "Password must be atleast 6 characters")
    private String password;
}