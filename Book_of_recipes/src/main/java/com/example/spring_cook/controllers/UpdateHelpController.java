package com.example.spring_cook.controllers;

import com.example.spring_cook.entities.Recipe;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateHelpController {
    @GetMapping("/getRecipeToUpdate")
    public Recipe getRecipeToUpdate(@RequestParam String name) {
        Recipe recipe = null;
        for (Recipe r: InitializeBaseController.getBook())
            if (r.getName().equals(name))
                recipe = r;
        return recipe;
    }
}
