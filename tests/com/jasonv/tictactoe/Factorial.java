package com.jasonv.tictactoe;

 /**
  * This program computes the factorial of a number
  */
 public class Factorial {                   // Define a class
   public static void main(String[] args) { // The program starts here
     int input = 3; // Get the user's input
     double result = factorial(input);      // Compute the factorial
     System.out.println(result);            // Print out the result
  }                                        // The main() method ends here
 
   public static double factorial(int x) {  // This method computes x!
     if (x < 0)                             // Check for bad input
       return 0.0;                          //   if bad, return 0
     double fact = 1.0;                     // Begin with an initial value
     while(x > 1) {                         // Loop until x equals 1
       fact = fact * x;                     //   multiply by x each time
       x = x - 1;                           //   and then decrement x
     }                                      // Jump back to start of loop
     return fact;                           // Return the result
   }                                        // factorial() ends here
 }
