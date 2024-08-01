import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

public class shaHash {

    public static final String[] initialHashValuesHexStringArr = {"6a09e667", "bb67ae85", "3c6ef372", "a54ff53a", "510e527f", "9b05688c", "1f83d9ab", "5be0cd19"};
    public static final String[] constants512Arr = {"428a2f98", "71374491", "b5c0fbcf", "e9b5dba5", "3956c25b", "59f111f1", "923f82a4", "ab1c5ed5",
            "d807aa98", "12835b01", "243185be", "550c7dc3", "72be5d74", "80deb1fe", "9bdc06a7", "c19bf174",
            "e49b69c1", "efbe4786", "0fc19dc6", "240ca1cc", "2de92c6f", "4a7484aa", "5cb0a9dc", "76f988da",
            "983e5152", "a831c66d", "b00327c8", "bf597fc7", "c6e00bf3", "d5a79147", "06ca6351", "14292967",
            "27b70a85", "2e1b2138", "4d2c6dfc", "53380d13", "650a7354", "766a0abb", "81c2c92e", "92722c85",
            "a2bfe8a1", "a81a664b", "c24b8b70", "c76c51a3", "d192e819", "d6990624", "f40e3585", "106aa070",
            "19a4c116", "1e376c08", "2748774c", "34b0bcb5", "391c0cb3", "4ed8aa4a", "5b9cca4f", "682e6ff3",
            "748f82ee", "78a5636f", "84c87814", "8cc70208", "90befffa", "a4506ceb", "bef9a3f7", "c67178f2"};


    public static int calculatePadLength(int messagelength) {
        int padMultiple = 512;
        int length = messagelength + 1 + 64;

        if (length > padMultiple) {
            while (padMultiple < length) {
                padMultiple += 512;

            }


        }
        return padMultiple / 8;


    }

    public static byte[][] preProcess(byte[] arr, int messageLength) {


        System.out.println(messageLength);
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(messageLength);
        byte[] result = buffer.array();


        int padByte = calculatePadLength(messageLength);
        byte[] padArr = new byte[padByte];
        System.out.println(padByte);
        System.arraycopy(arr, 0, padArr, 0, arr.length);
        padArr[arr.length] = (byte) 128;
        int counter = 0;
        for (int i = padArr.length - 8; i < padArr.length; i++) {

            padArr[i] = result[7 - counter];
            counter++;

        }
        //System.out.println(Arrays.toString(padArr));
        int sections512 = padArr.length / 64;
        byte[][] parts512 = new byte[sections512][64];
        for (int i = 0; i < sections512; i++) {
            for (int j = 0; j < 64; j++) {
                parts512[i][j] = padArr[(i * 64 + j)];
            }
        }
        return parts512;
    }

    public static byte[] asciiConversion(String str){
        byte[] arr = new byte[str.length()];
        for(int i = 0; i < str.length();i++){

            arr[i] = (byte)((int)str.charAt(i));

        }
return arr;


    }

    public static byte[][] prepareMessageSchedule(byte[][] arr) {
        byte[][][] newArr = new byte[arr.length][64][4];
        byte[][] workingVariablesArr = new byte[8][4];
        byte[][] hashArr = new byte[8][4];
        byte[] t1 = new byte[4];
        byte[] t2 = new byte[4];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 64; j++) {
                if (j < 16) {

                    newArr[i][j][0] = arr[i][j * 4];
                    newArr[i][j][1] = arr[i][j * 4 + 1];
                    newArr[i][j][2] = arr[i][j * 4 + 2];
                    newArr[i][j][3] = arr[i][j * 4 + 3];
                } else {
                    newArr[i][j] = bitwiseAdditionModulo2(sigma(newArr[i][j - 2], 17, 19, 10), newArr[i][j - 7], sigma(newArr[i][j - 15], 7, 18, 3), newArr[i][j - 16]);


                }

            }
            if (i == 0) {
                for (int j = 0; j < workingVariablesArr.length; j++) {
                    workingVariablesArr[j] = HexFormat.of().parseHex(initialHashValuesHexStringArr[j]);
                    hashArr[j] = HexFormat.of().parseHex(initialHashValuesHexStringArr[j]);;
                }

            }
            else{
                for (int j = 0; j < workingVariablesArr.length; j++) {
                    workingVariablesArr[j] = hashArr[j];

                }
            }
            for (int j = 0; j < 64; j++) {

                t1 = bitwiseAdditionModulo25units(workingVariablesArr[7],chooseBits(workingVariablesArr[4], workingVariablesArr[5], workingVariablesArr[6]),threeRotations(workingVariablesArr[4], 6, 11, 25),HexFormat.of().parseHex(constants512Arr[j]), newArr[i][j]);
              //  t1 = workingVariablesArr[7] + chooseBits(workingVariablesArr[4], workingVariablesArr[5], workingVariablesArr[6]) + threeRotations(workingVariablesArr[4], 6, 11, 25) + HexFormat.of().parseHex(constants512Arr[j]) + newArr[i][j]
               t2 = bitwiseAdditionModulo2(threeRotations(workingVariablesArr[0], 2, 13, 22),majorityBits(workingVariablesArr[0], workingVariablesArr[1], workingVariablesArr[2]),new byte[4],new byte[4]);
             //   t2 = threeRotations(workingVariablesArr[0], 2, 13, 22) + majorityBits(workingVariablesArr[0], workingVariablesArr[1], workingVariablesArr[2]);
                workingVariablesArr[7] = Arrays.copyOf(workingVariablesArr[6],workingVariablesArr[5].length);
                workingVariablesArr[6] = Arrays.copyOf(workingVariablesArr[5],workingVariablesArr[5].length);
                workingVariablesArr[5] =  Arrays.copyOf(workingVariablesArr[4],workingVariablesArr[4].length);
                workingVariablesArr[4] = bitwiseAdditionModulo2(workingVariablesArr[3],t1,new byte[4],new byte[4]);
                workingVariablesArr[3] = Arrays.copyOf(workingVariablesArr[2],workingVariablesArr[2].length);
                workingVariablesArr[2] = Arrays.copyOf(workingVariablesArr[1],workingVariablesArr[1].length);
                workingVariablesArr[1] = Arrays.copyOf(workingVariablesArr[0],workingVariablesArr[0].length);
                workingVariablesArr[0] = bitwiseAdditionModulo2(t2,t1,new byte[4],new byte[4]);

            }
            for (int j = 0; j < hashArr.length;j++){
                hashArr[j] = bitwiseAdditionModulo2(hashArr[j],workingVariablesArr[j],new byte[4],new byte[4]);

            }


        }
        return hashArr;


    }

    public static byte[] majorityBits(byte[] arr1, byte[] arr2, byte[] arr3) {

        String str1 = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        byte[] arr4 = new byte[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            str1 = Integer.toBinaryString(arr1[i]);
            str2 = Integer.toBinaryString(arr2[i]);
            str3 = Integer.toBinaryString(arr3[i]);
            if(str1.length()>8){
                str1 = str1.substring(str1.length()-8);
            }
            if(str2.length()>8){
                str2 = str2.substring(str2.length()-8);
            }
            if(str3.length()>8){
                str3 = str3.substring(str3.length()-8);
            }
            while (str1.length() < 8 || str2.length() < 8 || str3.length() < 8) {
                if (str1.length() < 8) {
                    str1 = "0" + str1;
                }
                if (str2.length() < 8) {
                    str2 = "0" + str2;
                }
                if (str3.length() < 8) {
                    str3 = "0" + str3;
                }
            }
            int counter0 = 0;
            int counter1 = 0;
            for (int j = 0; j < str1.length(); j++) {
                counter0 = 0;
                counter1 = 1;
                if (str1.charAt(j) == 0) {
                    counter0++;
                } else {
                    counter1++;
                }
                if (str2.charAt(j) == 0) {
                    counter0++;
                } else {
                    counter1++;
                }
                if (str3.charAt(j) == 0) {
                    counter0++;
                } else {
                    counter1++;
                }
                if (counter0 > counter1) {
                    str4 += 0;
                } else {
                    str4 += 1;
                }


            }
            arr4[i] = (byte) Integer.parseInt(str4, 2);
            str4 = "";
        }
        return arr4;


    }

    /* 0a
     * 1b
     * 2c
     * 3d
     * 4e
     * 5f
     * 6g
     * 7h
     * */

    public static byte[] chooseBits(byte[] arr1, byte[] arr2, byte[] arr3) {
        String str1 = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        byte[] arr4 = new byte[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            str1 = Integer.toBinaryString(arr1[i]);
            str2 = Integer.toBinaryString(arr2[i]);
            str3 = Integer.toBinaryString(arr3[i]);
            if(str1.length()>8){
                str1 = str1.substring(str1.length()-8);
            }
            if(str2.length()>8){
                str2 = str2.substring(str2.length()-8);
            }
            if(str3.length()>8){
                str3 = str3.substring(str3.length()-8);
            }
            while (str1.length() < 8 || str2.length() < 8 || str3.length() < 8) {
                if (str1.length() < 8) {
                    str1 = "0" + str1;
                }
                if (str2.length() < 8) {
                    str2 = "0" + str2;
                }
                if (str3.length() < 8) {
                    str3 = "0" + str3;
                }
            }
            str4 = "";
            for (int j = 0; j < str1.length(); j++) {
                if (str1.charAt(i) == 0) {
                    str4 += str3.charAt(i);
                } else {
                    str4 += str2.charAt(i);
                }

            }
            arr4[i] = (byte) Integer.parseInt(str4, 2);
        }
        return arr4;


    }

    public static byte[] threeRotations(byte[] arr, int firstRot, int secondRot, int thirdRot) {
        byte[] newArr = new byte[arr.length];
        newArr = rotation(arr, firstRot);
        newArr = rotation(newArr, secondRot);
        newArr = rotation(newArr, thirdRot);

        return newArr;
    }

    public static byte[] bitwiseAdditionModulo2(byte[] arr1, byte[] arr2, byte[] arr3, byte[] arr4) {
        byte[] arr5 = new byte[arr1.length];
        String str1 = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        String str5 = "";
        for (int i = 0; i < arr1.length; i++) {
            str1 = Integer.toBinaryString(arr1[i]);
            str2 = Integer.toBinaryString(arr2[i]);
            str3 = Integer.toBinaryString(arr3[i]);
            str4 = Integer.toBinaryString(arr4[i]);
            if(str1.length()>8){
                str1 = str1.substring(str1.length()-8);
            }
            if(str2.length()>8){
                str2 = str2.substring(str2.length()-8);
            }
            if(str3.length()>8){
                str3 = str3.substring(str3.length()-8);
            }
            if(str4.length()>8){
                str4 = str4.substring(str4.length()-8);
            }


            while (str1.length() < 8 || str2.length() < 8 || str3.length() < 8 || str4.length() < 8) {
                if (str1.length() < 8) {
                    str1 = "0" + str1;
                }
                if (str2.length() < 8) {
                    str2 = "0" + str2;
                }
                if (str3.length() < 8) {
                    str3 = "0" + str3;
                }
                if (str4.length() < 8) {
                    str4 = "0" + str4;
                }


            }
            str5 = "";
            for (int j = 0; j < str1.length(); j++) {
                int num = Integer.valueOf( str1.substring(j,j+1)) + Integer.valueOf(str2.substring(j,j+1)) + Integer.valueOf( str3.substring(j,j+1)) + Integer.valueOf( str4.substring(j,j+1));
                num %= 2;
                str5 += num;

            }
            arr5[i] = (byte) Integer.parseInt(str5, 2);


        }

        return arr5;
    }
    public static byte[] bitwiseAdditionModulo25units(byte[] arr1, byte[] arr2, byte[] arr3, byte[] arr4,byte[] arr6) {
        byte[] arr5 = new byte[arr1.length];
        String str1 = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        String str5 = "";
        String str6 = "";
        for (int i = 0; i < arr1.length; i++) {
            str1 = Integer.toBinaryString(arr1[i]);
            str2 = Integer.toBinaryString(arr2[i]);
            str3 = Integer.toBinaryString(arr3[i]);
            str4 = Integer.toBinaryString(arr4[i]);
            str6 = Integer.toBinaryString(arr6[i]);
            if(str1.length() > 8){
                str1 = str1.substring(str1.length()-8);
            }
            if(str2.length() > 8){
                str2 = str2.substring(str2.length()-8);
            }
            if(str3.length() > 8){
                str3 = str3.substring(str3.length()-8);
            }
            if(str4.length() > 8){
                str4 = str4.substring(str4.length()-8);
            }
            if(str6.length() > 8){
                str6 = str6.substring(str6.length()-8);
            }

            while (str1.length() < 8 || str2.length() < 8 || str3.length() < 8 || str4.length() < 8||str6.length()<8) {
                if (str1.length() < 8) {
                    str1 = "0" + str1;
                }
                if (str2.length() < 8) {
                    str2 = "0" + str2;
                }
                if (str3.length() < 8) {
                    str3 = "0" + str3;
                }
                if (str4.length() < 8) {
                    str4 = "0" + str4;
                }
                if(str6.length()<8){

                    str6 = "0"+ str6;
                }


            }
            str5 = "";
            for (int j = 0; j < str1.length(); j++) {
                int num = Integer.valueOf( str1.substring(j,j+1)) + Integer.valueOf( str2.substring(j,j+1)) + Integer.valueOf( str3.substring(j,j+1)) + Integer.valueOf( str4.substring(j,j+1))+Integer.valueOf(str6.substring(j,j+1));
                num %= 2;
                str5 += num;

            }
            arr5[i] = (byte) Integer.parseInt(str5, 2);


        }

        return arr5;
    }


    public static byte[] shift(byte[] arr, int num) {
        byte[] newArr = new byte[arr.length];
        String binary = "";
        String subString = "";
        for (int i = 0; i < arr.length; i++) {
            subString = Integer.toBinaryString((arr[i]));
            if(subString.length()>8){
                subString = subString.substring(subString.length()-8);
            }
            while (subString.length() < 8) {
                subString = "0" + subString;
            }

            binary += subString;

        }
        subString = "";
        for (int i = 0; i < num; i++) {
            subString += "0";
        }
        binary = subString + binary;
        for (int i = 0; i < arr.length; i++) {


            //System.out.println(binary.substring(i * 8, i * 8 + 8));

            newArr[i] = (byte) Integer.parseInt(binary.substring(i * 8, i * 8 + 8), 2);
        }

        return newArr;

    }

    public static byte[] sigma(byte[] arr, int rightRot1Num, int rightRot2Num, int shiftNum) {

        byte[] rightRot1Arr = rotation(arr, rightRot1Num);
        byte[] rightRot2Arr = rotation(arr, rightRot2Num);
        byte[] shiftArr = shift(arr, shiftNum);
        byte[] zeroArr = new byte[shiftArr.length];
        return bitwiseAdditionModulo2(rightRot1Arr, rightRot2Arr, shiftArr, zeroArr);


    }

    public static byte[] rotation(byte[] arr, int num) {
        byte[] newArr = new byte[arr.length];
        String binary = "";
        String subString = "";
        for (int i = 0; i < arr.length; i++) {
            subString = Integer.toBinaryString((arr[i]));
            if(subString.length()>8){
                subString=subString.substring(subString.length()-8);
            }
            while (subString.length() < 8) {
                subString = "0" + subString;
            }

            binary += subString;

        }
        System.out.println(binary);
        subString = binary.substring(binary.length() - num);
        binary = binary.substring(0, binary.length() - num);
        binary = subString + binary;
        System.out.println(binary);
        for (int i = 0; i < arr.length; i++) {


            //System.out.println(binary.substring(i * 8, i * 8 + 8));

            newArr[i] = (byte) Integer.parseInt(binary.substring(i * 8, i * 8 + 8), 2);
        }

        return newArr;


    }

}
