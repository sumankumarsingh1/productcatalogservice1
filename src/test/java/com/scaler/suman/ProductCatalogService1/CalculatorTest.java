package com.scaler.suman.ProductCatalogService1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void Test_Add_With_Two_Integer_Run_Successfully() {
        // Arrange
        Calculator calculator = new Calculator();

        //Act
        int result = calculator.add(21,39);

        //Assert
        assert (result==60);
    }


    @Test
    void Test_DivideByZero_ThrowsArithmaticException() {
        // Arrange
        Calculator calculator=new Calculator();
        //Act
        // int result = calculator.divide(30,0);

        //Assert
        assertThrows(ArithmeticException.class,
                ()-> calculator.divide(2,0));
    }
}