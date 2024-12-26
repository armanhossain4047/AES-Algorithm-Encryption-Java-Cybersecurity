 
package aes;
 
/**
 *
 * @author ARMAN HOSSAIN
 */

public class MatrixGenarator {
    
    
    // Method to calculate the required matrix size (square matrix)
    private static int calculateMatrixSize(int length) {
        return (int) Math.ceil(Math.sqrt(length));
    }
    // Method to convert a character to its decimal equivalent (A=1, B=2, ..., Z=26)
    private static int charToDecimal(char ch) {
        return Character.toUpperCase(ch) - 'A' + 1;
    }

    // Method to convert decimal to hexadecimal (2-digit format)
    private static String decimalToHex(int decimal) {
        return String.format("%02X", decimal);
    }

    public static String[][] textToHexMatrix(String inputText) {
    // Calculate matrix size
    int matrixSize = calculateMatrixSize(inputText.length());
    int totalCells = matrixSize * matrixSize;

    // Process each character and convert to hexadecimal
    StringBuilder hexValues = new StringBuilder();
    for (char ch : inputText.toCharArray()) {
        if (ch == ' ') {
            // Convert space to "2A"
            hexValues.append("2A ");
        } else {
            int decimalValue = charToDecimal(ch);
            String hexValue = decimalToHex(decimalValue);
            hexValues.append(hexValue).append(" ");
        }
    }

    // Fill remaining cells with "00" if input length < totalCells
    while (hexValues.toString().split(" ").length < totalCells) {
        hexValues.append("00 ");
    }

    // Create the matrix and populate it
    String[] hexArray = hexValues.toString().trim().split(" ");
    String[][] matrix = new String[matrixSize][matrixSize];

    int index = 0;
    for (int i = 0; i < matrixSize; i++) {
        for (int j = 0; j < matrixSize; j++) {
            matrix[i][j] = hexArray[index++];
        }
    }

    return matrix;
}
}
