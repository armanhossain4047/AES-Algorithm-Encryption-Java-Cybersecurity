package aes;
import java.util.Scanner;

/**
 *
 * @author ARMAN HOSSAIN
 */

public class TextToHexadecimalMatrix {
 
    // XOR two string matrices containing hexadecimal values
    public static String[][] AddRoundKey(String[][] matrix1, String[][] matrix2) {
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        String[][] result = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Parse hex strings to integers, XOR them, and convert back to hex string
                int value1 = Integer.parseInt(matrix1[i][j], 16);
                int value2 = Integer.parseInt(matrix2[i][j], 16);
                int xorValue = value1 ^ value2;
                result[i][j] = String.format("%02X", xorValue); // Convert back to 2-digit hex
            }
        }

        return result;
    }
  
}
