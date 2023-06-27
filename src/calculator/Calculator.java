/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculator;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author ABMEngine
 */
public class Calculator {
   private ArrayList<Double> operands;
   private ArrayList<String> operators;
   private double result;

   public Calculator() {
      this.operands = new ArrayList<>();
      this.operators = new ArrayList<>();
      this.result = 0.0;
   }

   public double getResult() {
      return result;
   }

   public void addOperand(double num) {
      operands.add(num);
   }

   public void addOperator(String operator) {
      operators.add(operator);
   }

   public void calculate() {
      if (operands.isEmpty()) {
         System.out.println("Tidak ada operand yang dimasukkan");
         return;
      }

      if (operands.size() - operators.size() != 1) {
         System.out.println("Jumlah operator tidak sesuai dengan jumlah operand");
         return;
      }

      // Mendahulukan perkalian dan pembagian
      int i = 0;
      while (i < operators.size()) {
         if (operators.get(i).equals("*") || operators.get(i).equals("/")) {
            double num1 = operands.get(i);
            double num2 = operands.get(i + 1);
            String operator = operators.get(i);

            switch (operator) {
               case "*" -> operands.set(i, num1 * num2);
               case "/" -> {
                   if (num2 == 0) {
                       System.out.println("Tidak bisa membagi dengan nol");
                       return;
                   }
                   operands.set(i, num1 / num2);
                 }
            }

            operands.remove(i + 1);
            operators.remove(i);
         } else {
            i++;
         }
      }

      // Menghitung sisa operasi matematika
      result = operands.get(0);

      for (i = 0; i < operators.size(); i++) {
         double num = operands.get(i + 1);
         String operator = operators.get(i);

         switch (operator) {
            case "+" -> result += num;
            case "-" -> result -= num;
         }
      }
   }

   public static void main(String[] args) {
      Calculator calculator = new Calculator();
      Scanner input = new Scanner(System.in);

      System.out.println("Masukkan operasi matematika: ");
      String operation = input.nextLine();

      String[] parts = operation.split(" ");
      try {
         for (int i = 0; i < parts.length; i++) {
            if (i % 2 == 0) {
               double operand = Double.parseDouble(parts[i]);
               calculator.addOperand(operand);
            } else {
               String operator = parts[i];
               if (!operator.matches("[+\\-*/]")) {
                  System.out.println("Operator tidak valid: " + operator);
                  return;
               }
               calculator.addOperator(operator);
            }
         }
         calculator.calculate();
         System.out.println("Hasil: " + calculator.getResult());
      } catch (NumberFormatException e) {
         System.out.println("Operand tidak valid: " + e.getMessage());
         return;
      }
   }
}