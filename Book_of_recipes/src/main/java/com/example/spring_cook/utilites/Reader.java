package com.example.spring_cook.utilites;

import com.example.spring_cook.entities.Recipe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Reader {
    public static LinkedList<Recipe> getCuisine(String path) throws IOException {
        LinkedList<Recipe> cuisine = new LinkedList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        Recipe recipe;
        while ((recipe = getRecipe(br)) != null)
            cuisine.add(recipe);
        return cuisine;
    }

    private static LinkedList<String> getList(String str){
        str = str.substring(1);
        String[] array = str.split(", ");
        return new LinkedList<>(Arrays.asList(array));
    }

    private static Recipe getRecipe(BufferedReader br) throws IOException{
        String name;
        if ((name = br.readLine()) == null)
            return null;
        String tagsString = br.readLine();
        LinkedList<String> tags = getList(tagsString);
        String cuisine = tags.get(0);
        String ingredientsString = br.readLine();
        LinkedList<String> ingredients = getList(ingredientsString);
        String technology = br.readLine().substring(1);
        int rating = Integer.parseInt(br.readLine().substring(1));
        br.readLine();
        return new Recipe(name, cuisine, ingredients, tags, technology, rating);
    }
}
