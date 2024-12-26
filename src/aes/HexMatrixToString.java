/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aes;

/**
 *
 * @author ARMAN HOSSAIN
 */
 public class HexMatrixToString {
    
     public static String hexMatrixToString(String[][] hexMatrix) {
    if (hexMatrix == null || hexMatrix.length == 0 || hexMatrix[0].length == 0) {
        throw new IllegalArgumentException("Hexadecimal matrix cannot be null or empty.");
    }

    int rows = hexMatrix.length;
    int cols = hexMatrix[0].length;
    char[][] charMatrix = new char[rows][cols];

    // Convert hexadecimal matrix to character matrix
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (hexMatrix[i][j] == null || hexMatrix[i][j].isEmpty()) {
                throw new IllegalArgumentException(
                        "Hexadecimal value at position [" + i + "][" + j + "] is null or empty.");
            }
            int decimalValue = Integer.parseInt(hexMatrix[i][j], 16); // Convert hex to decimal
            charMatrix[i][j] = (char) decimalValue;                  // Convert decimal to character
        }
    }

    // Build the resulting string in the specified order
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            result.append(charMatrix[i][j]);
        }
    }

    return result.toString();
}
}
