package com.fitapp.repository;

import com.fitapp.model.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementsRepository  extends JpaRepository<Measurements, Integer> {
}
