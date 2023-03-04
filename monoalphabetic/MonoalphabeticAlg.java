/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monoalphabetic;

import cryprography.PlayFair;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hp
 */
public class MonoalphabeticAlg {

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);

        System.out.println("Enter your plain text");
        String plainText = read.nextLine();
        Monoalphabetic mn = new Monoalphabetic();
        String cipherText = mn.encryption(plainText);
        plainText = mn.decryption(cipherText);

        System.out.println("cipher text is " + cipherText);
        System.out.println("plain text is " + plainText);

    }

}
