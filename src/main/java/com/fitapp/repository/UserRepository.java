package com.fitapp.repository;


import com.fitapp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findByName(String name);
}
