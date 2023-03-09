
package Autokey;
/**
 *
 * @author raneen
 */
public class Autokey {

    static char[] alph = {
        'a', 'b', 'c', 'd', 'e',
        'f', 'g', 'h', 'i', 'j',
        'k', 'l', 'm', 'n', 'o',
        'p', 'q', 'r', 's', 't',
        'u', 'v', 'w', 'x', 'y', 'z'
    };

    public String encryption(String plainText, String key) {
        plainText = plainText.toLowerCase();
        String cipherText = "";
        key = generateKey(key, plainText);
        for (int i = 0; i < plainText.length(); i++) {
            int indexOfPlainTextChara = search(plainText.charAt(i), alph);
            int indexOfKeyChara = search(key.charAt(i), alph);
            
            cipherText += alph[(indexOfKeyChara + indexOfPlainTextChara) % 26];

        }
        return cipherText;
    }

    public String decryption(String cipherText,String key) {
        cipherText = cipherText.toLowerCase();
        key = generateKey(key, cipherText);

        String plainText = "";
        
         for (int i = 0; i < cipherText.length(); i++) {
            int indexOfCipherTextChara = search(cipherText.charAt(i), alph);
            int indexOfKeyChara = search(key.charAt(i), alph);
            
            plainText += alph[(indexOfCipherTextChara - indexOfKeyChara +26) % 26];

        }
        return plainText;
    }

    private int search(char letter, char[] alpha) {
        for (int i = 0; i < alpha.length; i++) {
            if (alpha[i] == letter) {
                return i;
            }
        }
        return -1;
    }

    private String generateKey(String key, String plainText) {
        String newKey = key +plainText;

            if (newKey.length() > plainText.length()) {
                newKey = newKey.substring(0, plainText.length());
            }
        return newKey;
    }
}
