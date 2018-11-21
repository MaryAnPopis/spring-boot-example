package com.magnaalianza.repositories;

import com.magnaalianza.models.Farmland;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FarmlandRepository extends JpaRepository<Farmland, Long> {
    Farmland findTopByOrderByIdDesc();
}
