//Jordan Phan 4/29/2024
import java.math.BigInteger;
import java.util.*;

public class Encryption {
    private String[][] substitutionArr = new String[256][2];

    public Encryption(String seedString) {
        BigInteger seed = new BigInteger(seedString);
        Random random = new Random(seed.longValue());

        // Fill the array with unique pairs of 8-bit strings
        List<String> secondColumn = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            String bitString = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
            substitutionArr[i][0] = bitString;
            secondColumn.add(bitString);
        }

        // Shuffle the second column with the seeded Random instance
        Collections.shuffle(secondColumn, random);

        // Assign the shuffled values to the second column of the array
        for (int i = 0; i < 256; i++) {
            substitutionArr[i][1] = secondColumn.get(i);
        }
    }

    private String substitution(String plaintText) {
        //searches through every row in substitution arr in first column and compares it to plainText, if they are equal, return the arr in the second column of the same row
        for (int i = 0; i < substitutionArr.length; i++) {
            if (substitutionArr[i][0].equals(plaintText)) {
                return substitutionArr[i][1];
            }
        }
        //error
        return "-1";
    }

    private String substitutionReverse(String cipherText) {
        //searches through every row in substitution arr in first column and compares it to plainText, if they are equal, return the arr in the second column of the same row
        for (int i = 0; i < substitutionArr.length; i++) {
            if (substitutionArr[i][1].equals(cipherText)) {
                return substitutionArr[i][0];
            }
        }
        //error
        return "-1";
    }

    // convert byte to string
    public static String byteToString(byte value) {
        int offset = value & 0xFF;
        String newValue = Integer.toBinaryString(offset);
        //add 0's to the front of newValue until it is length of 4
        while (newValue.length() % 8 != 0) {
            newValue = "0" + newValue;
        }
        return newValue;


    }

    // ECB encryption method
   /* public byte[] ecbEncrypt(byte[] plainText) {
        byte[] cipherText = new byte[plainText.length];
        //use method substitution on each arr in plainText
        for (int i = 0; i < plainText.length; i++) {
            cipherText[i] = (byte) Integer.parseInt(substitution(byteToString(plainText[i])), 2);
        }
        return cipherText;
    }

    public byte[] ecbDecrypt(byte[] cipherText) {
        byte[] plainText = new byte[cipherText.length];
        for (int i = 0; i < cipherText.length; i++) {
            plainText[i] = (byte) Integer.parseInt(substitutionReverse(byteToString(cipherText[i])), 2);

        }
        return plainText;


    }*/

    // CBC Encryption method
    public byte[] cbcEncrypt(byte IV, byte[] plainText) {
        byte current;
        byte[] cipherText = new byte[plainText.length + 1];
        //go through every arr of plainText
        for (int i = 0; i < cipherText.length - 1; i++) {
            if (i == 0) {
                //xor IV and plainText in block i
                current = (byte) (IV ^ plainText[i]);
                //Encrypt current
                cipherText[i] = (byte) Integer.parseInt(substitution(byteToString(current)), 2);
            } else {
                //xor cipherText[i-1] and plainText in block i
                current = (byte) (cipherText[i - 1] ^ plainText[i]);
                //Encrypt current
                cipherText[i] = (byte) Integer.parseInt(substitution(byteToString(current)), 2);

            }
            //add encrypted IV to the end
        }

        cipherText[cipherText.length - 1] = IV;
        return cipherText;

    }

    public byte[] cbcDecrypt(byte[] cipherText) {
        byte IV;
        byte current;

        IV = (byte) cipherText[cipherText.length - 1];
        byte[] plainText = new byte[cipherText.length - 1];
        //go through every arr of plainText
        for (int i = 0; i < plainText.length; i++) {
            //Decrypt cipherText in block i
            current = (byte) Integer.parseInt(substitutionReverse(byteToString(cipherText[i])), 2);
            if (i == 0) {


                //xor IV and current
                plainText[i] = (byte) (IV ^ current);
            } else {
                //xor cipherText[i-1] and current
                plainText[i] = (byte) (cipherText[i - 1] ^ current);
            }
        }
        //plainText = Arrays.copyOf(plainText,plainText.length-1);
        return plainText;

    }

    //ctr encryption method
    public byte[] ctrEncrypt(byte IV, byte[] plainText) {
        byte initIV = IV;
        byte current;
        //+1 to the length to add space to add the IV at the end
        byte[] cipherText = new byte[plainText.length + 1];

        for (int i = 0; i < cipherText.length - 1; i++) {
            //substitution using the IV
            current = (byte) Integer.parseInt(substitution(byteToString(IV)), 2);
            // xor the substituted IV and plainText;
            cipherText[i] = (byte) (current ^ plainText[i]);
            //increment the IV or restart at 0 if IV = 255
            if ((IV & 0xFF) == 255) {
                IV = 0;
            } else {
                IV++;
            }

        }

        cipherText[cipherText.length - 1] = initIV;
        return cipherText;
    }

    //ctr decryption method
    public byte[] ctrDecrypt(byte[] cipherText) {

        byte IV = cipherText[cipherText.length - 1];
        byte current;
        //-1 to the length to remove the IV
        byte[] plainText = new byte[cipherText.length - 1];

        for (int i = 0; i < plainText.length; i++) {
            //substitution using the IV
            current = (byte) Integer.parseInt(substitution(byteToString(IV)), 2);
            // xor the substituted IV and the ciphertext
            plainText[i] = (byte) (current ^ cipherText[i]);
           //increment the IV or restart at 0 if IV = 255
            if ((IV & 0xFF) == 255) {
                IV = 0;
            } else {
                IV++;
            }

        }
        return plainText;
    }

    //converts a string of bits into an array of bytes each hold 8 bits
    public static byte[] parseIntoByteArr(String text) {

        byte[] textArr = new byte[(text.length() / 8)];
        for (int i = 0; i < textArr.length; i++) {
            textArr[i] = (byte) Integer.parseInt(text.substring(i * 8, i * 8 + 8), 2);


        }
        return textArr;
    }

    //convert an array of bytes into a string of corresponding bits
    public static String parseByteArrToString(byte[] textArr) {
        String text = "";
        for (int i = 0; i < textArr.length; i++) {
            text += byteToString(textArr[i]) + " ";
        }
        return text;

    }

    public String[][] getSubstitutionArr() {
        return substitutionArr;
    }

    //convert hex to binary
    static String hexToBin(String s) {
        return new BigInteger(s, 16).toString(2);
    }

}
