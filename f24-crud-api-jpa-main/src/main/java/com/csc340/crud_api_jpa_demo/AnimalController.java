package com.csc340.crud_api_jpa_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    // Get all animals
    @GetMapping
    public String getAllAnimals(Model model) {
        List<Animal> animals = animalService.getAllAnimals();
        model.addAttribute("animalList", animals);
        return "animal-list"; // Return the name of the view (HTML file)
    }

    // Get animal details
    @GetMapping("/{id}")
    public String getAnimalById(@PathVariable int id, Model model) {
        Animal animal = animalService.getAnimalById(id);
        if (animal == null) {
            return "404"; // Handle not found
        }
        model.addAttribute("animal", animal);
        return "animal-details"; // Return the name of the view (HTML file)
    }

    // Display the create animal form
    @GetMapping("/create")
    public String showCreateAnimalForm(Model model) {
        model.addAttribute("animal", new Animal()); // Initialize a new Animal object
        return "animal-create"; // Return the name of the create animal view (HTML file)
    }

    // Create a new animal
    @PostMapping("/new")
    public String createAnimal(@ModelAttribute Animal animal) {
        animalService.addAnimal(animal);
        return "redirect:/animals"; // Redirect to the animal list after creation
    }

    // Display the update animal form
    @GetMapping("/update/{id}")
    public String showUpdateAnimalForm(@PathVariable int id, Model model) {
        Animal animal = animalService.getAnimalById(id);
        if (animal == null) {
            return "404"; // Handle not found
        }
        model.addAttribute("animal", animal);
        return "animal-update"; // Return the name of the update animal view (HTML file)
    }

    // Update an existing animal
    @PostMapping("/update")
    public String updateAnimal(@ModelAttribute Animal animal) {
        animalService.updateAnimal(animal.getAnimalId(), animal);
        return "redirect:/animals/" + animal.getAnimalId(); // Redirect to the animal details page after updating
    }

    // Delete an animal
    @GetMapping("/delete/{id}")
    public String deleteAnimal(@PathVariable int id) {
        animalService.deleteAnimal(id);
        return "redirect:/animals"; // Redirect to the animal list after deletion
    }
}
