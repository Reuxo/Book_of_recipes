package com.example.spring_cook.controllers;

import com.example.spring_cook.entities.Recipe;
import com.example.spring_cook.utilites.Writer;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

@Controller
public class Task4Controller {
    @GetMapping("/add")
    public String add() {
        return "addRecipe";
    }

    @GetMapping("/addRecipe")
    public String forthA(Authentication auth, @RequestParam String name, @RequestParam String cuisine,
                         @RequestParam String type, @RequestParam String addIngrTA, @RequestParam String addTagTA,
                         @RequestParam String technology, @RequestParam String link, @RequestParam int rating)
            throws IOException {
        LinkedList<String> getTagList = getTags(cuisine, type, addTagTA);
        LinkedList<String> ingr = new LinkedList<>(Arrays.asList(addIngrTA.split(",")));
        if (auth.getPrincipal() != null) {
            Recipe recipe = new Recipe(name, cuisine, ingr, getTagList, technology, link, rating);
            Writer.writeFile(recipe);
            return "addRecipe";
        } else
            return "index";
    }

    @GetMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("listNames", InitializeBaseController.getListNames());
        return "deleteRecipe";
    }

    @GetMapping("/deleteRecipe")
    public String forthB(Authentication auth, Model model, @RequestParam String name) throws IOException {
        InitializeBaseController.deleteRecipeByName(name);

        Writer.refreshBase(InitializeBaseController.getBook());
        InitializeBaseController.initListNames();
        model.addAttribute("listNames", InitializeBaseController.getListNames());
        return "deleteRecipe";
    }

    @GetMapping("/update")
    public String update(Model model){
        LinkedList<String> tags = new LinkedList<>();
        for (int i = 2; i < InitializeBaseController.getBook().get(0).getTags().size(); i++)
            tags.add(InitializeBaseController.getBook().get(0).getTags().get(i));
        model.addAttribute("listNames", InitializeBaseController.getListNames());
        model.addAttribute("firstRecipe", InitializeBaseController.getBook().get(0));
        model.addAttribute("tags", tags);
        return "updateRecipe";
    }

    @GetMapping("/updateRecipe")
    public String updateRecipe(Model model, @RequestParam String formerName, @RequestParam String futureName,
                               @RequestParam String cuisine, @RequestParam String ingredientsTA,
                               @RequestParam String type, @RequestParam String tagsTA,
                               @RequestParam String technology, @RequestParam String link, @RequestParam int rating) throws IOException {
        LinkedList<String> ingr = new LinkedList<>(Arrays.asList(ingredientsTA.split(",")));
        LinkedList<String> tags = getTags(cuisine, type, tagsTA);
        Recipe recipe = new Recipe(futureName, cuisine, ingr, tags, technology, link, rating);
        InitializeBaseController.updateRecipe(recipe, formerName);
        Writer.refreshBase(InitializeBaseController.getBook());
        InitializeBaseController.initListNames();

        LinkedList<String> tagsList = new LinkedList<>();
        for (int i = 2; i < InitializeBaseController.getBook().get(0).getTags().size(); i++)
            tagsList.add(InitializeBaseController.getBook().get(0).getTags().get(i));
        model.addAttribute("listNames", InitializeBaseController.getListNames());
        model.addAttribute("firstRecipe", InitializeBaseController.getBook().get(0));
        model.addAttribute("tags", tagsList);
        return "updateRecipe";
    }


    private LinkedList<String> getTags(String cuisine, String type, String tags) {
        LinkedList<String> listTags = new LinkedList<>();
        listTags.add(cuisine);
        listTags.add(type);
        String[] arr = tags.split(",");
        listTags.addAll(Arrays.asList(arr));
        return listTags;
    }
}


