package com.csc340.crud_api_jpa_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    // Get all animals
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    // Get an animal by its ID
    public Animal getAnimalById(int id) {
        Optional<Animal> animal = animalRepository.findById(id);
        return animal.orElse(null);
    }

    // Add a new animal
    public Animal addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    // Update an existing animal
    public Animal updateAnimal(int id, Animal animalDetails) {
        Animal animal = getAnimalById(id);
        if (animal != null) {
            animal.setName(animalDetails.getName());
            animal.setScientificName(animalDetails.getScientificName());
            animal.setSpecies(animalDetails.getSpecies());
            animal.setHabitat(animalDetails.getHabitat());
            animal.setDescription(animalDetails.getDescription());
            return animalRepository.save(animal);
        }
        return null;
    }

    // Delete an existing animal
    public void deleteAnimal(int id) {
        animalRepository.deleteById(id);
    }
}
