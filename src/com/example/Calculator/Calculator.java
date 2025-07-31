package com.example.Calculator;

public class Calculator {

    public int add(String numbers){
        if(numbers.isEmpty()){
            return 0;           // returnsZero_whenEmptyString
        }

        String[] parts = numbers.split(",|\\n");
        int sum = 0;
        for(String part : parts){            // whenTwoNumbers
            sum+= Integer.parseInt(part);
        }
        return sum;
    }
}
