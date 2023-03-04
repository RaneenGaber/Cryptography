package cryprography;

import java.util.Scanner;

/**
 *
 * @author raneen
 */
public class PlayFairAlg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner read = new Scanner(System.in);
        System.out.println("Enter your key");
        String key = read.nextLine();
        System.out.println("Enter your plain text");
        String plainText = read.nextLine();
        PlayFair pl = new PlayFair();
        String cipherText = pl.playfairEncryption(key, plainText);
        System.out.println("cipher text is " + cipherText);
        System.out.println("plain text is " + pl.playfairDecryption(key, cipherText));
    }
}
