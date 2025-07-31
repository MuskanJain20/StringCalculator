package com.example.Calculator;

import java.util.regex.Pattern;

public class Calculator {

    public int add(String numbers){
        if(numbers.isEmpty()){
            return 0;           // returnsZero_whenEmptyString
        }

        String delimiter = ",|\\n";   // returnsVal_whenNewLineAsDelimiter
        if(numbers.startsWith("//")){    // returnsVal_whenCustomDelimiter
            int end = numbers.indexOf("\n");
            delimiter = Pattern.quote(numbers.substring(2,end));
            numbers = numbers.substring(end+1);
        }
        
        String[] parts = numbers.split(delimiter);
        int sum = 0;
        for(String part : parts){            // whenTwoNumbers
            sum+= Integer.parseInt(part);
        }
        return sum;
    }
}
