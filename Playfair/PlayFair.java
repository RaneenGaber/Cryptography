package cryprography;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author raneen
 */
public class PlayFair {

    static String[] alph = {
        "a", "b", "c", "d", "e",
        "f", "g", "h", "i", "j",
        "k", "l", "m", "n", "o",
        "p", "q", "r", "s", "t",
        "u", "v", "w", "x", "y", "z"
    };
    static List listOfAlpha = new ArrayList(Arrays.asList(alph));

    private static String removeDuplicateCharacter(String key) {
        key = key.replaceAll(" ", "");
        key = key.toLowerCase();
        String Newkey = "";
        Newkey += key.charAt(0);
        for (int i = 1; i < key.length(); i++) {
            if (Newkey.indexOf(key.charAt(i)) == -1) {
                Newkey += key.charAt(i);
            }
        }
        return Newkey;
    }

    private static String[][] playfair_Key_Matrix(String key) {
        String[][] matrix = new String[5][5];

        boolean key_is_end = false;
        for (int i = 0; i < matrix.length; i++) {
            String subKey;
            subKey = key.substring(i * 5);
            if (subKey.length() == 0) {
                key_is_end = true;
                break;
            } else if (subKey.length() < 5) {
                subKey = key.substring(i * 5);
            } else {
                subKey = key.substring(i * 5, i * 5 + 5);
            }
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = Character.toString(subKey.charAt(j));

                if (subKey.length() - 1 == j && subKey.length() < 5) {

                    key_is_end = true;
                    break;
                }
            }
            if (subKey.indexOf("i") != -1) {
                matrix[i][subKey.indexOf("i") % 5] = "ij";
            }
            if (subKey.indexOf("j") != -1) {
                matrix[i][subKey.indexOf("j") % 5] = "ij";
            }
            if (key_is_end) {
                break;
            }
        }

        if (key_is_end) {
            int index = 0;
            int j = (key.length() % 5);
            for (int i = (key.length() / 5); i < matrix.length; i++) {
                for (; j < matrix.length; j++) {
                    matrix[i][j] = (String) listOfAlpha.get(index++);

                }
                j = 0;
            }
        }
        printMatrix(matrix);
        return matrix;
    }

    private static void printMatrix(String[][] matrix) {
        System.out.println("matrix represntation is ");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {

                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }

    private static String remove_i_or_j(String key) {
        int indexI = key.indexOf("i");
        int indexJ = key.indexOf("j");
        int max = Math.max(indexI, indexJ);

        String newKey = "";
        if (indexI == indexJ) {
            return key;
        }
        if (indexI == -1 || indexJ == -1) {
            return key;
        }
        newKey = key.substring(0, max) + key.substring(max + 1);
        return newKey;
    }

    private static void removeKeyFromList(String key, List<String> alpha) {
        if (key.indexOf("i") != -1 || key.indexOf("j") != -1) {
            for (int j = 0; j < alpha.size(); j++) {

                if ('i' == alpha.get(j).charAt(0)) {
                    alpha.remove(j);
                }
                if ('j' == alpha.get(j).charAt(0)) {
                    alpha.remove(j);
                }
            }
        } else {
            for (int j = 0; j < alpha.size(); j++) {
                if ('i' == alpha.get(j).charAt(0)) {
                    alpha.set(j, "ij");

                } else if ('j' == alpha.get(j).charAt(0)) {
                    alpha.remove(j);

                }

            }
        }

        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < alpha.size(); j++) {
                if (key.charAt(i) == alpha.get(j).charAt(0) && (key.charAt(i) != 'i' || key.charAt(i) != 'j')) {
                    alpha.remove(j);
                }
            }
        }
    }

    private static ArrayList<String> formatText(String plainText) {
        plainText = plainText.toLowerCase();
        plainText = plainText.replaceAll(" ", "");

        ArrayList<String> spliteText = new ArrayList<>();
        String pair;
        int count = 0;
        while (count < plainText.length()) {
            pair = "";
            pair += plainText.charAt(count++);
            if (count == plainText.length()) {
                pair += "x";
            } else if (plainText.charAt(count) != pair.charAt(0)) {
                pair += plainText.charAt(count++);
            } else {
                pair += "x";
            }
            spliteText.add(pair);
        }
        return spliteText;
    }

    private static String convertLetterIntoCipher(ArrayList<String> spliteText, String[][] playfairMatrix) {
        String cipherText = "";

        for (int i = 0; i < spliteText.size(); i++) {
            Point letter1 = search(Character.toString(spliteText.get(i).charAt(0)), playfairMatrix);
            Point letter2 = search(Character.toString(spliteText.get(i).charAt(1)), playfairMatrix);
            int i1 = (int) letter1.getX();
            int j1 = (int) letter1.getY();
            int i2 = (int) letter2.getX();
            int j2 = (int) letter2.getY();
            //in same row
            if (i1 == i2) {
                j1 = (j1 + 1) % 5;
                j2 = (j2 + 1) % 5;

            } //in same column
            else if (j1 == j2) {
                i1 = (i1 + 1) % 5;
                i2 = (i2 + 1) % 5;

            } //not  in same raw and column
            else {
                int temp = j1;
                j1 = j2;
                j2 = temp;
            }
            cipherText += playfairMatrix[i1][j1];
            cipherText += playfairMatrix[i2][j2];
        }
        return cipherText;
    }

    private static String convertCipherIntoLetter(ArrayList<String> spliteText, String[][] playfairMatrix) {
        String plainText = "";

        for (int i = 0; i < spliteText.size(); i++) {
            Point letter1 = search(Character.toString(spliteText.get(i).charAt(0)), playfairMatrix);
            Point letter2 = search(Character.toString(spliteText.get(i).charAt(1)), playfairMatrix);
            int i1 = (int) letter1.getX();
            int j1 = (int) letter1.getY();
            int i2 = (int) letter2.getX();
            int j2 = (int) letter2.getY();
            //in same row
            if (i1 == i2) {
                j1 = (j1 + 4) % 5;
                j2 = (j2 + 4) % 5;

            } //in same column
            else if (j1 == j2) {
                i1 = (i1 + 4) % 5;
                i2 = (i2 + 4) % 5;

            } //not  in same raw and column
            else {
                int temp = j1;
                j1 = j2;
                j2 = temp;
            }
            plainText += playfairMatrix[i1][j1];
            plainText += playfairMatrix[i2][j2];
        }
        return plainText;
    }

    private static Point search(String letter, String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j].contains("j") && letter.charAt(0) == 'j') {
                    return new Point(i, j);

                }
                if (matrix[i][j].charAt(0) == letter.charAt(0)) {
                    // System.out.println("point" + new Point(i,j));
                    return new Point(i, j);
                }
            }
        }
        return new Point(-1, -1);
    }

    public static String playfairEncryption(String key, String plainText) {
        key = removeDuplicateCharacter(key);
        key = remove_i_or_j(key);
        removeKeyFromList(key, listOfAlpha);

        ArrayList<String> spliteText = formatText(plainText);
        String[][] playfairMatrix = playfair_Key_Matrix(key);
        String cipherText = convertLetterIntoCipher(spliteText, playfairMatrix);
        return cipherText;
    }

    public static String playfairDecryption(String key, String ciperText) {
        key = removeDuplicateCharacter(key);
        key = remove_i_or_j(key);
        removeKeyFromList(key, listOfAlpha);
        String[][] playfairMatrix = playfair_Key_Matrix(key);

        ArrayList<String> spliteText = formatText(ciperText);
        String plainText = convertCipherIntoLetter(spliteText, playfairMatrix);
        return plainText;
    }
}
