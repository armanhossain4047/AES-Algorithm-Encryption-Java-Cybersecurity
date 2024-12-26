/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aes;

/**
 *
 * @author ARMAN HOSSAIN
 */

public class KeyGenarator {
    
    public static String[][] keyGenerate(String[][] matrix, String[] Gvalue) {
    int rows = matrix.length;   
    int cols = matrix[0].length;   

    // Initialize the result key matrix
    String[][] newKey = new String[rows][cols];

    // Initialize the first row by XORing the first row of the matrix with Gvalue
    newKey[0] = xorArrays(matrix[0], Gvalue);

    // Compute the remaining rows dynamically
    for (int i = 1; i < rows; i++) {
        newKey[i] = xorArrays(newKey[i - 1], matrix[i]);
    }
    return newKey;
}
    
public static String[] xorArrays(String[] array1, String[] array2) {
        String[] result = new String[array1.length];
        for (int i = 0; i < array1.length; i++) {
            int value1 = Integer.parseInt(array1[i], 16);
            int value2 = Integer.parseInt(array2[i], 16);
            int xorValue = value1 ^ value2;
            result[i] = String.format("%02X", xorValue); // Format as 2-digit hexadecimal
        }
        return result;
    }

    public static String[] xorStringArrays(String[] array1, String[] array2) {

        String[] result = new String[array1.length];

        for (int i = 0; i < array1.length; i++) {
            // Parse hexadecimal strings into integers
            int value1 = Integer.parseInt(array1[i], 16);
            int value2 = Integer.parseInt(array2[i], 16);

            // XOR the integers
            int xorValue = value1 ^ value2;

            // Convert the XOR result back to a hexadecimal string and store it
            result[i] = String.format("%02X", xorValue);
        }

        return result;
    }
}
