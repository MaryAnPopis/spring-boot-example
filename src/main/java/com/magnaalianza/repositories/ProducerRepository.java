package com.magnaalianza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magnaalianza.models.Producer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProducerRepository extends JpaRepository<Producer, Long>{
    List<Producer> findByFirstnameContaining(String name);
}
