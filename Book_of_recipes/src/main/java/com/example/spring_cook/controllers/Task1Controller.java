package com.example.spring_cook.controllers;

import com.example.spring_cook.entities.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.Objects;

@Controller
public class Task1Controller {
    @GetMapping("/firstA")
    public String infoRecipe(Model model, @RequestParam(required = false) String name){
        Recipe recipe = getInfoRecipe(name);
        if(recipe != null) {
            model.addAttribute("cuisine", recipe.getCuisine());
            model.addAttribute("type", recipe.getType());
            model.addAttribute("ingredients", recipe.getIngredients());
            model.addAttribute("name", recipe.getName());
        }
        return "Task1AB";
    }

    @GetMapping("/firstB")
    public String linkRecipe(Model model, @RequestParam(required = false) String name){
        Recipe recipe = getInfoRecipe(name);
        if(recipe != null) {
            model.addAttribute("name", recipe.getName());
            model.addAttribute("technology", recipe.getTechnology());
            model.addAttribute("link", recipe.getLink());
        }
        return "Task1AB";
    }

    @GetMapping("/firstC")
    public String ingredientsRecipe(Model model, @RequestParam String ingr1,
                                    @RequestParam(required = false) String ingr2,
                                    @RequestParam(required = false) String ingr3,
                                    @RequestParam(required = false) String ingr4){
        LinkedList<String> list = new LinkedList<>();
        list.add(ingr1);
        if(!Objects.equals(ingr2, ""))
            list.add(ingr2);
        if(!Objects.equals(ingr3, ""))
            list.add(ingr3);
        if(!Objects.equals(ingr4, ""))
            list.add(ingr4);
        LinkedList<Recipe> listIngr = getRecipeForIngr(list);

        if(!listIngr.isEmpty()){
            model.addAttribute("listIngr", listIngr);
        }
        return "Task1C";
    }

    @GetMapping("/firstD")
    public String randomeRecipe(Model model){
        Recipe recipe = getRandomRecipe();
        model.addAttribute("recipe", recipe);
        return "Task1D";
    }


    private static Recipe getInfoRecipe(String name) {
        for (Recipe recipe : InitializeBaseController.getBook())
            if (recipe.getName().equals(name))
                return recipe;
        return null;
    }
    private static LinkedList<Recipe> getRecipeForIngr(LinkedList<String> ingr){
        LinkedList<Recipe> list = new LinkedList<>();
        boolean contains;
        for(Recipe recipe : InitializeBaseController.getBook()) {
            contains = true;
            for (String ingredient : ingr) {
                if (!recipe.getIngredients().contains(ingredient)) {
                    contains = false;
                    break;
                }
            }
            if (contains)
                list.add(recipe);
        }
        return list;
    }
    private static Recipe getRandomRecipe(){
        int index = (int) (Math.random() * InitializeBaseController.getBook().size()) + 1;
        return InitializeBaseController.getBook().get(index);
    }
}
