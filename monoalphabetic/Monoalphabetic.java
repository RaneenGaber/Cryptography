package monoalphabetic;

/**
 *
 * @author hp
 */
public class Monoalphabetic {

    static String[] alph = {
        "a", "b", "c", "d", "e",
        "f", "g", "h", "i", "j",
        "k", "l", "m", "n", "o",
        "p", "q", "r", "s", "t",
        "u", "v", "w", "x", "y", "z"
    };
    static String[] cipher = {
        "q", "w", "e", "r", "t",
        "y", "u", "i", "o", "p",
        "a", "s", "d", "f", "g",
        "h", "j", "k", "l", "z",
        "x", "c", "v", "b", "n", "m"
    };

    public String encryption(String plainText) {
        plainText = plainText.toLowerCase();
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i++) {
            String charater = plainText.charAt(i) + "";
            int index = search(charater, alph);
            if (index == -1) {
                cipherText += charater;
            } else {
                cipherText += cipher[index];
            }
        }
        return cipherText;
    }

    public String decryption(String cipherText) {
        cipherText = cipherText.toLowerCase();
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++) {
            String charater = cipherText.charAt(i) + "";
            int index = search(charater, cipher);
            if (index == -1) {
                plainText += charater;
            } else {
                plainText += alph[index];
            }
        }
        return plainText;
    }

    private int search(String letter, String[] alpha) {
        for (int i = 0; i < alpha.length; i++) {
            if (alpha[i].charAt(0) == letter.charAt(0)) {
                return i;
            }
        }
        return -1;
    }

}
