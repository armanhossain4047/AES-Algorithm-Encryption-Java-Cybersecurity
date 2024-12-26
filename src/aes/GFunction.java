/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aes;

/**
 *
 * @author ARMAN HOSSAIN
 */
public class GFunction {

    private static final String[] R_Constrant = {"01", "02", "04", "08", "10", "20", "40", "80", "1B", "36"};
      
    public static String[] G_Function(String[] input) {
        // Step 1: Perform a left circular shift
        String[] shiftedArray = leftCircularShift(input);

        // Step 2: Perform SubBytes transformation
        String[] subBytesArray = SubBytes(shiftedArray);

        // Step 3: XOR with another array (e.g., {"01", "00", "00"})
        return xorWithArray(subBytesArray);
    }

    // Left circular shift
    public static String[] leftCircularShift(String[] array) {
        String[] shifted = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            shifted[i] = array[(i + 1) % array.length];
        }
        return shifted;
    }

    // SubBytes transformation
    // SubBytes transformation with error handling
public static String[] SubBytes(String[] input) {
    if (input == null || input.length == 0) {
        throw new IllegalArgumentException("Input array cannot be null or empty");
    }

    String[] result = new String[input.length];

    for (int i = 0; i < input.length; i++) {
        try {
            if (input[i] == null || input[i].isEmpty()) {
                throw new IllegalArgumentException("Input value at index " + i + " is null or empty");
            }

            int value = Integer.parseInt(input[i], 16); // Parse hexadecimal string to integer
            int row = (value >> 4) & 0x0F; // Extract high nibble
            int col = value & 0x0F;        // Extract low nibble
            result[i] = SUB_BYTES_TABLE[row][col]; // Look up substitution value
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid hexadecimal input at index " + i + ": " + input[i], e);
        }
    }

    return result;
}


    // XOR with another array
    public static String[] xorWithArray(String[] array) {

        String[] result = new String[array.length];
        String[] xorArray = new String[array.length];
        xorArray[0] = R_Constrant[AES.RConstant_Coutn++];
        for (int i = 1; i < array.length; i++) {
            xorArray[i] = "00";
        }
        for (int i = 0; i < array.length; i++) {
            int value1 = Integer.parseInt(array[i], 16); // Parse hexadecimal string to integer
            int value2 = Integer.parseInt(xorArray[i], 16); // Parse hexadecimal string to integer
            result[i] = String.format("%02X", value1 ^ value2); // XOR and format back to hexadecimal string
        }
        return result;
    }

    private static final String[][] SUB_BYTES_TABLE = {
        {"63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76"},
        {"CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "C0"},
        {"B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15"},
        {"04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75"},
        {"09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84"},
        {"53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF"},
        {"D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8"},
        {"51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2"},
        {"CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73"},
        {"60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14", "DE", "5E", "0B", "DB"},
        {"E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91", "95", "E4", "79"},
        {"E7", "CB", "3B", "56", "D8", "5E", "49", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE", "08"},
        {"BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A"},
        {"70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E"},
        {"E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF"},
        {"8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16"}
    };

    public static String[][] RoundSubBytes(String[][] inputMatrix) {
        String[][] resultMatrix = new String[inputMatrix.length][inputMatrix[0].length];

        for (int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix[i].length; j++) {
                // Parse the hexadecimal input
                int hexValue = Integer.parseInt(inputMatrix[i][j], 16);

                // Determine the row and column based on hexadecimal value
                int row = (hexValue >> 4) & 0x0F; // High nibble
                int col = hexValue & 0x0F;        // Low nibble

                // Get the corresponding SubBytes value from the table
                resultMatrix[i][j] = SUB_BYTES_TABLE[row][col];
            }
        }

        return resultMatrix;
    }

    public static String[][] leftCircularShift(String[][] matrix) {
        // Create a new matrix to store the shifted rows
        String[][] shiftedMatrix = new String[matrix.length][matrix[0].length];

        // Iterate over each row of the matrix
        for (int row = 0; row < matrix.length; row++) {

            for (int col = 0; col < matrix[row].length; col++) {
                shiftedMatrix[row][col] = matrix[row][(col + row) % matrix[row].length];
            }
        }
        return shiftedMatrix;
    }

    public static String[][] InversMatrix(String[][] Matrix) {
        String[][] Temp = new String[Matrix.length][Matrix.length];
        for (int i = 0; i < Matrix.length; i++) {
            for (int j = 0; j < Matrix.length; j++) {
                Temp[i][j] = Matrix[j][i];
            }
        }
        return Temp;
    }

    public static String[][] multiplyHexMatrices(String[][] matrix1, String[][] matrix2) {
    int size = matrix1.length; // Assuming square matrices
    String[][] result = new String[size][size];

    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            int sum = 0; // Intermediate sum for multiplication
            for (int k = 0; k < size; k++) {
                int value1 = Integer.parseInt(matrix1[i][k], 16);
                int value2 = Integer.parseInt(matrix2[k][j], 16);
                sum ^= value1 * value2; // XOR sum for Galois Field multiplication
            }
            result[i][j] = String.format("%02X", sum & 0xFF); // Store result in hexadecimal
        }
    }
    return result;
}


}
