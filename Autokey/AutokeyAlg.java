package Autokey;

import java.util.Scanner;

/**
 *
 * @author raneen
 */
public class AutokeyAlg {

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);

        System.out.println("Enter your plain text");
        String plainText = read.nextLine();
        System.out.println("Enter your key ");
        String key = read.nextLine();


        Autokey autoKey = new Autokey();
        String cipherText = autoKey.encryption(plainText,key);
        plainText = autoKey.decryption(cipherText,key);
        System.out.println("cipher text is " + cipherText);
        System.out.println("plain text is " + plainText);
    }
}
