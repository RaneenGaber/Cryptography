package Vigenère;

import java.util.Scanner;

/**
 *
 * @author Raneen
 */
public class VigenèreAlg {

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);

        System.out.println("Enter your plain text");
        String plainText = read.nextLine();
        System.out.println("Enter your key ");
        String key = read.nextLine();

        Vigenère vig = new Vigenère();
        String cipherText = vig.encryption(plainText,key);
        plainText = vig.decryption(cipherText,key);
        System.out.println("cipher text is " + cipherText);
        System.out.println("plain text is " + plainText);

    }

}
