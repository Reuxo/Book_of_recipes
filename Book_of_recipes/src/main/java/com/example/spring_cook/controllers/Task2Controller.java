package com.example.spring_cook.controllers;

import com.example.spring_cook.entities.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class Task2Controller {

    static class SortByRating implements Comparator<Recipe>{
        public int compare(Recipe a, Recipe b){
            return b.getRating() - a.getRating();
        }
    }

    @GetMapping("/secondA")
    public String secondA(Model model){
        model.addAttribute("allTags", getAllTags());
        return "Task2A";
    }

    public static Set<String> getAllTags(){
        Set<String> tagsSet = new TreeSet<>();
        for(Recipe recipe : InitializeBaseController.getBook())
            tagsSet.addAll(recipe.getTags());
        return tagsSet;
    }

    @GetMapping("/secondB")
    public String secondB(Model model, @RequestParam(required = false) String tag){
        LinkedList<Recipe> topRecipe = new LinkedList<>();
        for(Recipe recipe : InitializeBaseController.getBook())
            if(recipe.getTags().contains(tag))
                topRecipe.add(recipe);
        topRecipe.sort(new SortByRating());
        while (topRecipe.size() > 3)
            topRecipe.removeLast();
        model.addAttribute("top3", topRecipe);
        return "Task2B";
    }

    @GetMapping("/secondC")
    public String secondC(Model model) {
        String[] tags = {"первые блюда", "вторые блюда", "десерты"};
        LinkedList<Recipe> recipes = new LinkedList<>();
        for (String tag : tags)
            recipes.add(getRandomDishByTag(tag));
        model.addAttribute("dinner", recipes);
        return "Task2C";
    }

    @GetMapping("/secondD")
    public String secondD(Model model) {
        model.addAttribute("recipe", getRandomDishByTag("завтраки"));
        return "Task2D";
    }

    private static Recipe getRandomDishByTag(String tag){
        LinkedList<Recipe> recipesByTag = new LinkedList<>();
        for (Recipe recipe : InitializeBaseController.getBook())
            if(recipe.getTags().contains(tag))
                recipesByTag.add(recipe);
        int randomIndex = (int) (Math.random()*recipesByTag.size());
        return recipesByTag.get(randomIndex);
    }

    @GetMapping("/secondE")
    public String secondE(Model model, @RequestParam String ingr1,
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
        LinkedList<Recipe> recipe10 = getRecipeForIgnorIngr(list);
        if(!recipe10.isEmpty()){
            while (recipe10.size() > 10)
                recipe10.removeLast();
            model.addAttribute("recipe10", recipe10);
        }
        return "Task2E";
    }

    private static LinkedList<Recipe> getRecipeForIgnorIngr(LinkedList<String> ingr){
        LinkedList<Recipe> list = new LinkedList<>();
        boolean contains;
        for(Recipe recipe : InitializeBaseController.getBook()) {
            contains = true;
            for (String ingredient : ingr) {
                if (recipe.getIngredients().contains(ingredient)) {
                    contains = false;
                    break;
                }
            }
            if (contains)
                list.add(recipe);
        }
        return list;
    }

}
