package com.labs1904;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SecretRecipeDecoder {
    private static Map<String, String> ENCODING = new HashMap<String, String>() {
        {
            put("y", "a");
            put("h", "b");
            put("v", "c");
            put("x", "d");
            put("k", "e");
            put("p", "f");
            put("z", "g");
            put("s", "h");
            put("a", "i");
            put("b", "j");
            put("e", "k");
            put("w", "l");
            put("u", "m");
            put("q", "n");
            put("n", "o");
            put("l", "p");
            put("m", "q");
            put("f", "r");
            put("o", "s");
            put("i", "t");
            put("g", "u");
            put("j", "v");
            put("t", "w");
            put("d", "x");
            put("r", "y");
            put("c", "z");
            put("3", "0");
            put("8", "1");
            put("4", "2");
            put("0", "3");
            put("2", "4");
            put("7", "5");
            put("5", "6");
            put("9", "7");
            put("1", "8");
            put("6", "9");
        }
    };

    /**
     * Given a string named str, use the Caesar encoding above to return the decoded string.
     * @param str
     * @return
     */
    public static String decodeString(String str) {

        StringBuilder emptyString = new StringBuilder();

        for (char s: str.toCharArray()) {
            String toChars = ENCODING.getOrDefault(String.valueOf(s),String.valueOf(s));
            emptyString.append(toChars);
        }
        return emptyString.toString();


    }

    /**
     * Given an ingredient, decode the amount and description, and return a new Ingredient
     * @param line
     * @return
     */
    public static Ingredient decodeIngredient(String line) {
       // return new Ingredient("1 cup", "butter");
        String[] splitter = line.split("#");
        String parts = decodeString(splitter[0]);
        String ingredient = decodeString(splitter[1]);

        return new Ingredient(parts, ingredient);
    }

    public static void main(String[] args) {
        try {
            List<String> decodedLines = Files.readAllLines(Paths.get("src/main/resources/secret_recipe.txt"))
                    .stream()
                    .filter(line -> !line.trim().isEmpty())
                    .map(line -> {
                        Ingredient decoded = decodeIngredient(line);
                        return decoded.getAmount() + " " + decoded.getDescription();
                    })
                    .collect(Collectors.toList());

            Files.write(Paths.get("decoded_recipe.txt"), decodedLines);
            System.out.println("Recipe decoded successfully!");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
