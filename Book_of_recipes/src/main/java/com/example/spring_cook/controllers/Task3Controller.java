package com.example.spring_cook.controllers;

import com.example.spring_cook.entities.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class Task3Controller {
    public static Set<String> getAllCuisine(){
        Set<String> tagsSet = new TreeSet<>();
        for(Recipe recipe : InitializeBaseController.getBook())
            tagsSet.add(recipe.getCuisine());
        return tagsSet;
    }
    public LinkedList<Recipe> getRecipeByCuisine(String cuisine){
        LinkedList<Recipe> topRecipe = new LinkedList<>();
        for(Recipe recipe : InitializeBaseController.getBook())
            if(recipe.getCuisine().contains(cuisine))
                topRecipe.add(recipe);
        topRecipe.sort(new Task2Controller.SortByRating());
        return topRecipe;
    }

    @GetMapping("/thirdA")
    public String thirdA(Model model, @RequestParam String cuisine){
        LinkedList<Recipe> list = getRecipeByCuisine(cuisine);
        while (list.size() > 3)
            list.removeLast();
        model.addAttribute("top3Cuisine", list);
        return "Task3A";
    }

    @GetMapping("/thirdB")
    public String thirdB(Model model){
        Set<String> set = getAllCuisine();
        LinkedList<String> list = new LinkedList<>(set);
        int[] rating = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            rating[i] = cuisineRating(list.get(i));
        }
        int index = 0;
        for(int i = 1; i < rating.length - 1; i++){
            if(rating[i] < rating[index]){
                index = i;
            }
        }
        list.remove(index);
        model.addAttribute("top3Cuisine", list);
        return "Task3B";
    }
    private int cuisineRating(String cuisine){
        int rating = 0;
        for(Recipe recipe : InitializeBaseController.getBook()){
            if(recipe.getCuisine().equals(cuisine)) {
                rating += recipe.getRating();
            }
        }
        return rating;
    }




}
