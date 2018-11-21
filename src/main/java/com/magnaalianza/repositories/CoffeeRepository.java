package com.magnaalianza.repositories;

import com.magnaalianza.models.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
