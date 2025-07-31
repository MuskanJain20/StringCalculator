package com.example.Calculator;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    public void add_returnsZero_whenEmptyString(){
        assertEquals(0,calculator.add(""));
    }

    @Test
    public void add_returnsVal_whenSingleNumber(){
        assertEquals(1,calculator.add("1"));
    }

    @Test
    public void add_returnsVal_whenTwoNumbers(){
        assertEquals(6,calculator.add("1,5"));
    }

    @Test
    public void add_returnsVal_whenNewLineAsDelimiter(){
        assertEquals(6,calculator.add("1\n2,3"));
    }

    @Test
    public void add_returnsVal_whenCustomDelimiter(){
        assertEquals(3,calculator.add("//;\n1;2"));
    }

    @Test
    public void add_throwsError_whenNegativeNumbers(){
        Exception ex = assertThrows(IllegalArgumentException.class, ()-> calculator.add("1,-2,-3"));
        assertEquals("Negative numbers not allowed",ex.getMessage());
    }

}