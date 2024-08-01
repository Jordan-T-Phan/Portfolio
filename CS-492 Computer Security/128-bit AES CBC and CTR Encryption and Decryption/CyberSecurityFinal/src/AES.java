import java.util.Arrays;

//Jordan Phan 4/29/2024
public class AES {
    //s-box lookup table

     static final int[][] sBox =
            {{0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76}
                    , {0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0}
                    , {0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15}
                    , {0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75}
                    , {0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84}
                    , {0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf}
                    , {0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8}
                    , {0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2}
                    , {0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73}
                    , {0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb}
                    , {0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79}
                    , {0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08}
                    , {0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a}
                    , {0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e}
                    , {0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf}
                    , {0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16}};
    //inverse of sbox look up table
    static final int[][] sInverse = {
            {0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb},
            {0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb},
            {0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e},
            {0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25},
            {0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92},
            {0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84},
            {0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06},
            {0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b},
            {0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73},
            {0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e},
            {0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b},
            {0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4},
            {0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f},
            {0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef},
            {0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61},
            {0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d
            }};
    //rcon lookup table
    static final int[] rcon = {0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36};

    //adds new elements of byte = 0 so that it's divisible by 16
    public static byte[] padByteArr(byte[] plainText) {
        //1 byte is necessary for padbyte which is appended at the end of the file
        int length = 17 - ((plainText.length+1) % 16);
        //returns a byteArr with added length
        byte[] padding = new byte[length];
        byte[] newArr = new byte[plainText.length + length];
        System.arraycopy(plainText, 0, newArr, 0, plainText.length);
        System.arraycopy(padding, 0, newArr, plainText.length, padding.length);
        newArr[newArr.length-1] = (byte) length;
      //  System.out.println(Arrays.toString(newArr));
        return newArr;

    }

    //converts the byte[] plaintext to 4x4 byte arrs stored in a third arr
    public static byte[][][] convertToKeyStates(byte[] plainText) {
        int count = 0;
        int keystatesSize = plainText.length / 16;
        byte[][][] keyStates = new byte[keystatesSize][4][4];
        //keystate conversion
        for (int i = 0; i < keystatesSize; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    keyStates[i][k][j] = plainText[count];
                    count++;
                }
            }


        }
        return keyStates;
    }

    //rotword function
    public static byte[] rotWord(byte[][] keyState) {
        byte[] rotword = new byte[4];
        //cycle shifts elements in the last col  upward  when stored in new arr
        for (int i = 0; i < rotword.length - 1; i++) {
            rotword[i] = keyState[i + 1][3];
        }
        rotword[3] = keyState[0][3];
        return rotword;

    }

    //subbyte function takes a arr and a flag the tells whether to use the sbox or the inversesbox
    public static byte[] subByte(byte[] rotwordArr, boolean inverse) {
        byte[] subByteArr = new byte[4];
        for (int i = 0; i < subByteArr.length; i++) {
            // System.out.println(0xff & rotwordArr[i]);
            //get the hex value of the current element
            String hex = Integer.toHexString((0xff & rotwordArr[i]));
//adds 0 to the hex if it isn't big enough
            while (hex.length() < 2) {
                hex = "0" + hex;
            }
//takes the last two char if the hex is too large
            if (hex.length() > 2) {
                hex = hex.substring(hex.length() - 2);
            }
            //  System.out.println("hex" + hex);
            //row == the value of the hex[0]
            int row = Integer.parseInt(String.valueOf(hex.charAt(0)), 16);
            //col = the value of the hex[1]
            int col = Integer.parseInt(String.valueOf(hex.charAt(1)), 16);
            //checks whether to use inversbox or sbox
            if (!inverse) {
                subByteArr[i] = (byte) sBox[row][col];
            } else {
                subByteArr[i] = (byte) sInverse[row][col];
            }
        }
        return subByteArr;

    }

    //uses subByte function to calculate whole subbyteArr
    public static byte[][] subByteArr(byte[][] keyState, boolean inverse) {
        byte[][] rotatedArr = new byte[keyState.length][keyState[0].length];
        //rotates the arr for easier element access
        for (int i = 0; i < rotatedArr.length; i++) {
            for (int j = 0; j < rotatedArr[0].length; j++) {
                rotatedArr[i][j] = keyState[j][i];
            }
        }
        byte[][] subByteArr1 = new byte[rotatedArr.length][rotatedArr[0].length];
        //puts the subbyteArr in the ith column
        for (int i = 0; i < subByteArr1.length; i++) {
            subByteArr1[i] = subByte(rotatedArr[i], inverse);
        }
        byte[][] subByteArr2 = new byte[rotatedArr.length][rotatedArr[0].length];
        //rotates back the columns
        for (int i = 0; i < subByteArr2.length; i++) {
            for (int j = 0; j < subByteArr2[0].length; j++) {
                subByteArr2[i][j] = subByteArr1[j][i];
            }
        }
        return subByteArr2;
    }

    //rcon function
    public static byte[][] rcon(byte[][] keystate, byte[] subByteArr, int rconNum) {
        byte[][] rconArr = new byte[4][4];

        for (int j = 0; j < rconArr.length; j++) {
            if (j == 0) {
                //xors the first column with the subytearr, rcon lookup table, and the first column of the keystate
                for (int i = 0; i < rconArr.length; i++) {
                    //xors withe the rcon lookup table if i =0
                    if (i == 0) {
                        rconArr[i][0] = (byte) (0xff & (keystate[i][0] ^ (subByteArr[i])) ^ (rcon[rconNum]));
                    } else {
                        rconArr[i][0] = (byte) (keystate[i][0] ^ (subByteArr[i]));
                    }
                }
            } else {
                for (int i = 0; i < rconArr.length; i++) {
                    rconArr[i][j] = (byte) (keystate[i][j] ^ rconArr[i][j - 1]);

                }
            }
        }
        // System.out.println("RconARR " + Arrays.deepToString(rconArr));
        return rconArr;
    }

    //cycle left shifts each row of the arr based off what row number it is
    public static byte[][] shiftRows(byte[][] keystate) {

        byte[][] newKeystate = new byte[4][4];
        int initJ = 0;
        for (int i = 0; i < keystate.length; i++) {
            byte[] temp = new byte[4];
            for (int j = 0; j < keystate[i].length; j++) {
                //left shifts rows that don't overflow
                if (j + i < keystate[i].length) {
                    temp[j] = keystate[i][j + i];
                    initJ = j;
                    //deals with left shift overflow and puts it in remaining spots
                } else {
                    temp[j] = keystate[i][j - initJ - 1];
                }
            }
            newKeystate[i] = temp;

        }
        return newKeystate;

    }

    //cycle right shifts each row of the arr based off what row number it is
    public static byte[][] inverseShiftRows(byte[][] keystate) {

        byte[][] newKeystate = new byte[4][4];
        for (int i = 0; i < keystate.length; i++) {
            byte[] temp = new byte[4];
            for (int j = 0; j < keystate[i].length; j++) {
                //right shifts elements that don't overflow
                if (j - i >= 0) {
                    temp[j] = keystate[i][j - i];
                    //deals with overflowed right shifted elements
                } else {
                    temp[j] = keystate[i][keystate.length - i + j];
                }
            }
            newKeystate[i] = temp;

        }
        return newKeystate;

    }


    //based off csharp implementation from wikipedia
    private static byte GMul(byte a, byte b) { // Galois Field (256) Multiplication of two Bytes
        byte p = 0;

        for (int counter = 0; counter < 8; counter++) {
            if ((b & 1) != 0) {
                p ^= a;
            }

            boolean hi_bit_set = (a & 0x80) != 0;
            a <<= 1;
            if (hi_bit_set) {
                a ^= 0x1B; /* x^8 + x^4 + x^3 + x + 1 */
            }
            b >>= 1;
        }

        return p;
    }


    //mix columns function based off csharp implementation from wikipedia
    //takes the dot product of a keystate and a predetermined matrix
    public static byte[][] mixColumns(byte[][] keystate) {
        byte[][] newKeystate = new byte[4][4];
        //System.out.println("GMUL "+GMul((byte) 0x02, (byte) 87));
        for (int i = 0; i < keystate[0].length; i++) {
            //note: uses GMUL function to calculate multiplication of two bytes
            //2*Arr[0][i] xor 3*Arr[1][i] xor Arr[2][i] xor Arr[3][i]
            newKeystate[0][i] = (byte) (GMul((byte) 0x02, keystate[0][i]) ^ GMul((byte) 0x03, keystate[1][i]) ^ keystate[2][i] ^ keystate[3][i]);
            //Arr[0][i] xor 2*Arr[1][i] xor 3*Arr[2][i] xor Arr[3][i]
            newKeystate[1][i] = (byte) (keystate[0][i] ^ GMul((byte) 0x02, keystate[1][i]) ^ GMul((byte) 0x03, keystate[2][i]) ^ keystate[3][i]);
            //Arr[0][i] xor Arr[1][i] xor 2*Arr[2][i] xor 3* Arr[3][i]
            newKeystate[2][i] = (byte) (keystate[0][i] ^ keystate[1][i] ^ GMul((byte) 0x02, keystate[2][i]) ^ GMul((byte) 0x03, keystate[3][i]));
            //3*Arr[0][i] xor Arr[1][i] xor Arr[2][i] xor 2*Arr[3][i]
            newKeystate[3][i] = (byte) (GMul((byte) 0x03, keystate[0][i]) ^ keystate[1][i] ^ keystate[2][i] ^ GMul((byte) 0x02, keystate[3][i]));
        }
        return newKeystate;

    }

    //inverse MixColumns implementation based off of csharp implementation from wikipedia
    //takes the dot product of a keystate and a predetermined matrix
    public static byte[][] inverseMixColumns(byte[][] keystate) {
        byte[][] newKeystate = new byte[4][4];
        //System.out.println("GMUL "+GMul((byte) 0x02, (byte) 87));
        for (int i = 0; i < keystate[0].length; i++) {
            //note: uses GMUL function to calculate multiplication of two bytes
            //14*Arr[0][i] xor 11*Arr[1][i] xor 13*Arr[2][i] xor 9*Arr[3][i]
            newKeystate[0][i] = (byte) (GMul((byte) 0x0E, keystate[0][i]) ^ GMul((byte) 0x0B, keystate[1][i]) ^ GMul((byte) 0x0D, keystate[2][i]) ^ GMul((byte) 0x09, keystate[3][i]));
            //9*Arr[0][i] xor 14*Arr[1][i] xor 11*Arr[2][i] xor 13*Arr[3][i]
            newKeystate[1][i] = (byte) (GMul((byte) 0x09, keystate[0][i]) ^ GMul((byte) 0x0E, keystate[1][i]) ^ GMul((byte) 0x0B, keystate[2][i]) ^ GMul((byte) 0x0D, keystate[3][i]));
            //13*Arr[0][i] xor 9*Arr[1][i] xor 14*Arr[2][i] xor 11*Arr[3][i]
            newKeystate[2][i] = (byte) (GMul((byte) 0x0D, keystate[0][i]) ^ GMul((byte) 0x09, keystate[1][i]) ^ GMul((byte) 0x0E, keystate[2][i]) ^ GMul((byte) 0x0B, keystate[3][i]));
            //11*Arr[0][i] xor 13*Arr[1][i] xor 9*Arr[2][i] xor 14*Arr[3][i]
            newKeystate[3][i] = (byte) (GMul((byte) 0xB, keystate[0][i]) ^ GMul((byte) 0x0D, keystate[1][i]) ^ GMul((byte) 0x09, keystate[2][i]) ^ GMul((byte) 0x0E, keystate[3][i]));
        }
        return newKeystate;

    }

//AES encryption

    public static byte[][][] AESEncrypt(String key, byte[][][] plainText) {
        //Generate Roundkeys
        byte[][][] roundKeyArr = keyGeneration(key);
        byte[][][] keyState = new byte[plainText.length][plainText[0].length][plainText[0][0].length];
        //byte [][] tempKeyState = new byte[plainText[0].length][plainText[0][0].length];
        //xors each 2d plaintext arr with the roundKeyArr[0]
        //initialize keystate
        for (int i = 0; i < keyState.length; i++) {
           // if (i == roundKeyArr.length - 10) {
            //    System.out.println("monkey" + Arrays.deepToString(keyState[i]));
            //}
            keyState[i] = xor2KeyState(roundKeyArr[0], plainText[i]);

        }

        //rounds
        for (int i = 1; i < roundKeyArr.length; i++) {
            for (int j = 0; j < keyState.length; j++) {

                //subByte function

                keyState[j] = subByteArr(keyState[j], false);
                //shiftRows function

                keyState[j] = shiftRows(keyState[j]);

                //no mixcolumns last round

                if (i != roundKeyArr.length - 1) {
                    //mixColumns function
                    keyState[j] = mixColumns(keyState[j]);
                }


                //Add roundKey
                keyState[j] = xor2KeyState(keyState[j], roundKeyArr[i]);
            }
        }
        return keyState;


    }

    //AES Decryption
    public static byte[][][] AESDecrypt(String key, byte[][][] plainText) {

        byte[][][] roundKeyArr = keyGeneration(key);
        byte[][][] keyState = new byte[plainText.length][plainText[0].length][plainText[0][0].length];
        //initialize keystate
        for (int i = 0; i < keyState.length; i++) {
            keyState[i] = xor2KeyState(roundKeyArr[10], plainText[i]);

        }
       // //}

        // byte[][][] newKeyState = new byte[plainText.length][plainText[0].length][plainText[0][0].length];
        //10 rounds
        for (int i = 0; i < 10; i++) {
            //goes through each keystate
            for (int j = 0; j < keyState.length; j++) {

                //invers shift row
                keyState[j] = inverseShiftRows(keyState[j]);

                // subyteArr
                keyState[j] = subByteArr(keyState[j], true);



                //addRoundKey
                keyState[j] = xor2KeyState(keyState[j], roundKeyArr[10 - i-1]);
              //  if(i ==9){
                //    System.out.println("HelloAll " + Arrays.deepToString(keyState[j]));}
                if (i != 9) {
                    keyState[j] = inverseMixColumns(keyState[j]);
                }


            }
        }
        return keyState;


    }

    //key expansion into 11 keystate arrays
    private static byte[][][] keyGeneration(String key) {
        byte[] stringKey = key.getBytes();
        byte[][][] roundKeyArr = new byte[11][4][4];
        roundKeyArr[0] = convertToKeyStates(stringKey)[0];
        for (int i = 1; i < roundKeyArr.length; i++) {
            //sub byte column
            byte[] subByteArr = subByte(rotWord(roundKeyArr[i - 1]), false);

            roundKeyArr[i] = rcon(roundKeyArr[i - 1], subByteArr, i - 1);
        }
        return roundKeyArr;
    }

    //xors 2 keystates together
    public static byte[][] xor2KeyState(byte[][] keystate1, byte[][] keystate2) {
        byte[][] newKeyState = new byte[keystate1.length][keystate1[0].length];
        for (int i = 0; i < keystate1.length; i++) {
            for (int j = 0; j < keystate1[i].length; j++) {
                //each element at [i,j] from each arr is xor together
                newKeyState[i][j] = (byte) (keystate1[i][j] ^ keystate2[i][j]);
            }
            //  System.out.println(Arrays.deepToString(newKeyState));

        }
        return newKeyState;


    }

    //converts a 3d byte arr to a 1d byte arr
    public static byte[] convertToSingleByteArr(byte[][][] byteArr) {
        int size = (byteArr.length * byteArr[0].length * byteArr[0][0].length);
        byte[] newByte = new byte[size];
        int count = 0;
        for (int i = 0; i < byteArr.length; i++) {
            for (int j = 0; j < byteArr[i].length; j++) {
                for (int k = 0; k < byteArr[i][j].length; k++) {
                    newByte[count++] = byteArr[i][k][j];

                }
            }
        }
        return newByte;
    }

}

