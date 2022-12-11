package com.example.spring_cook.utilites;

import com.example.spring_cook.entities.Recipe;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.Timer;

public class Writer {
    final static String files = "Cuisine.txt";

    public static void writeFile(Recipe recipe) throws IOException {
        String string = "\n";
        string = string.concat(recipe.getName() + "\n-");
        string = string.concat(getStringFromCollection(recipe.getTags()) + "\n-");
        string = string.concat(getStringFromCollection(recipe.getIngredients()) + "\n-");
        string = string.concat(recipe.getTechnology() + "\n-");
        string = string.concat(recipe.getRating() + "\n");
        Files.writeString(Paths.get(files), string, StandardOpenOption.APPEND);
    }

    private static String getStringFromCollection(LinkedList<String> list) {
        String ingr = "";
        for (String ingredients : list) {
            ingr = ingr.concat(ingredients + ", ");
        }
        return ingr.substring(0, ingr.length() - 2);
    }


    public static void refreshBase(LinkedList<Recipe> recipes) throws IOException {
//        writeFirstRecipe(recipes.get(0));
//        for(int i = 1; i < recipes.size(); i ++) {
//            writeFile(recipes.get(i));
//        }

        boolean isFirst = true;
        FileWriter fileWriter = new FileWriter(files, false);

        for (int i = 0; i < recipes.size(); i++) {
            if (i == 1) {
                isFirst = false;
            }
            fileWriter.write(getStringRecipe(recipes.get(i), isFirst));
        }
        fileWriter.close();

    }

    public static String getStringRecipe(Recipe recipe, boolean isFirst) throws IOException {
        String string = "\n";
        if (isFirst) {
            string = "";
        }
        string = string.concat(recipe.getName() + "\n-");
        string = string.concat(getStringFromCollection(recipe.getTags()) + "\n-");
        string = string.concat(getStringFromCollection(recipe.getIngredients()) + "\n-");
        string = string.concat(recipe.getTechnology() + "\n-");
        string = string.concat(recipe.getRating() + "\n");
        return string;
    }
}
