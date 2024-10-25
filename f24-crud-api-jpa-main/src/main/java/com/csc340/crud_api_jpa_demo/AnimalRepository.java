package com.csc340.crud_api_jpa_demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    List<Animal> findBySpecies(String species);
    List<Animal> findByHabitat(String habitat);
    List<Animal> findByNameContaining(String name);
}
