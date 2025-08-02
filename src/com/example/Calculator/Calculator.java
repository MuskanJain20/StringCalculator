package com.example.Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiterRegex = ",|\\n"; // default delimiter
        if (numbers.startsWith("//")) {
            int newlineIndex = numbers.indexOf("\n");
            String delimiterSection = numbers.substring(2, newlineIndex);
            numbers = numbers.substring(newlineIndex + 1); // remaining numbers part

            // Support multiple delimiters [***][%][@]
            List<String> delimiters = new ArrayList<>();
            Matcher matcher = Pattern.compile("\\[(.*?)]").matcher(delimiterSection);
            while (matcher.find()) {
                delimiters.add(Pattern.quote(matcher.group(1)));
            }

            // If single delimiter with no brackets like "//;\n1;2"
            if (delimiters.isEmpty()) {
                delimiters.add(Pattern.quote(delimiterSection));
            }

            delimiterRegex = String.join("|", delimiters);
        }

        String[] parts = numbers.split(delimiterRegex);
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();

        for (String part : parts) {
            if (part.isEmpty()) continue;
            int number = Integer.parseInt(part);
            if (number < 0) {
                negatives.add(number);
            }
            sum += number;
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed: " + negatives.toString().replaceAll("[\\[\\]]", ""));
        }

        return sum;
    }
}
