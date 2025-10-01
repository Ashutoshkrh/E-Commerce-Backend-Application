package com.globazaar.userservice.repository;

import com.globazaar.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Tells Spring that this is a Repository bean
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository gives us methods like save(), findById(), deleteById() for free!
}