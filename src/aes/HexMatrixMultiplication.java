/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aes;

/**
 *
 * @author ARMAN HOSSAIN
 */
 public class HexMatrixMultiplication {

    public static String[][] multiplyHexMatrices(String[][] matrix1, String[][] matrix2) {
        String[][] result = new String[matrix1.length][matrix2[0].length];

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                int sum = 0;
                for (int k = 0; k < matrix1[0].length; k++) {
                    int value1 = Integer.parseInt(matrix1[i][k], 16); // Convert hex to int
                    int value2 = Integer.parseInt(matrix2[k][j], 16); // Convert hex to int
                    sum += value1 * value2; // Multiply and accumulate
                }
                result[i][j] = String.format("%02X", sum); // Convert the sum back to hex
            }
        }

        return result;
    } 
}