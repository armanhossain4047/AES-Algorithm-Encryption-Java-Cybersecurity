/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aes;

import java.util.Scanner;

/**
 *
 * @author ARMAN HOSSAIN
 */
public class AES{
    public static int RConstant_Coutn = 0;
    
// reverse input message for generate daynamic initial key 0
    public static String reverseString(String input) {
        StringBuilder reversed = new StringBuilder(input);
        return reversed.reverse().toString();
    }

    public static void main(String[] args) {
       
        Scanner input = new Scanner(System.in);
        System.out.print("Enter You Message: ");
        String Message = input.nextLine();
        
        //convert input message to hexadecimal matrsaoiffix
        String[][] InputState = MatrixGenarator.textToHexMatrix(Message);
        
        //for key 0 genarate 
        String reversed = reverseString(Message);
        String[][] Key = MatrixGenarator.textToHexMatrix(reversed);
        
        //Find Round 0 Matrix
        String[][] Round = TextToHexadecimalMatrix.AddRoundKey(InputState, Key);
         
       //Initialize Necassery Arry  
        String[] GArray = new String[Round.length];
        String[][] AftersubBytes = new String[Round.length][Round.length];
        String[][] SheftRow = new String[Round.length][Round.length];
    
         //Use this loop to Find Round 1 to Round 10
        for (int x = 0; x < 10; x++) {
            //This loop give GArray For Calculate GFunction
            for (int i = 0; i <Round.length ; i++) {
                GArray[i] = Key[2][i];
            }
            
            //This Method will return GValue after calculate
            String[] gValue = GFunction.G_Function(GArray);
            
            // Genarate Key of x
            Key = KeyGenarator.keyGenerate(Key, gValue);
            
            //After SubBytes
            AftersubBytes = GFunction.RoundSubBytes(Round);
            //Inverse Matrix if you want you will Inverse it when generate matrix
            AftersubBytes = GFunction.InversMatrix(AftersubBytes);
            
            //Left CircularShift
            SheftRow = GFunction.leftCircularShift(AftersubBytes);
            if (x != 9) {
                Round = GFunction.multiplyHexMatrices(AftersubBytes, AftersubBytes);
            }
            Round = TextToHexadecimalMatrix.AddRoundKey(Round, Key);
        }
        String Encryption = HexMatrixToString.hexMatrixToString(Round);
        System.out.println("Final State:");
        for (String[] row : Round) {
            for (String value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        
        System.out.println("Encryption Message is:" + Encryption);
    }
}