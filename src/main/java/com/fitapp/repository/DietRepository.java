package com.fitapp.repository;

import com.fitapp.model.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepository extends JpaRepository<Diet , Integer> {
}
