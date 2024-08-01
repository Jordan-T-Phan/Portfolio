//Jordan Phan 4/29/2024

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class main1 {
    public static void main(String[] args) throws IOException {
        byte[] newByte = new byte[]{1, 2, 3};
        String pathString = "";
        String newPathString = "";
        int answer = -1;
        String IV = "";
        String key = "";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert the file path: ");
        pathString = scanner.nextLine();
        Path path = Paths.get(
                pathString);
        byte[] arr = Files.readAllBytes(path);
       // System.out.println(Arrays.toString(arr));
        System.out.print("Please insert ending file path: ");
        newPathString = scanner.nextLine();
        File endFile = new File(newPathString);

        //option select
        System.out.println("What would you like to do?");
        System.out.println("0 - CBC AES Encrypt");
        System.out.println("1 - CBC AES Decrypt");
        System.out.println("2 - CTR AES Encrypt");
        System.out.println("3 - CTR AES Decrypt");
        answer = Integer.parseInt(scanner.nextLine());
        System.out.println("What is the Key of the block cipher?");
        String passCode = scanner.nextLine();


        String passCodeString = String.valueOf(passCode.hashCode());
        Encryption Encryptor = new Encryption(passCodeString);
//AES CBC Encryption
        if (answer == 0) {
            byte[] cbcEncryption = Encryptor.cbcEncrypt((byte) new SecureRandom().generateSeed(1)[0], arr);
            while (key.length() != 16) {
                System.out.println("What is your AES key?It must be 16 chars: ");
                key = scanner.nextLine();
            }
            // System.out.println(Arrays.toString(arr));
            byte[] newArr = AES.padByteArr(cbcEncryption);
           // System.out.println(Arrays.toString(newArr));
            byte[][][] newArr1 = AES.convertToKeyStates(newArr);
            byte[][][] newArr2 = AES.AESEncrypt(key, newArr1);
            // System.out.println(newArr2.length + " "+newArr2[0].length+" "+newArr2[0][0].length);
            byte[] fileArr = AES.convertToSingleByteArr(newArr2);
            writeByte(fileArr, endFile);
           // writeByte(Encryptor.cbcEncrypt((byte) new SecureRandom().generateSeed(1)[0], arr), endFile);
       //AES CBC decryption
        } else if (answer == 1) {
            while (key.length() != 16) {
                System.out.println("What is your AES key? It must be 16 char: ");
                key = scanner.nextLine();
            }
            byte[][][] newArr1 = AES.convertToKeyStates(arr);
            byte[][][] newArr2 = AES.AESDecrypt(key, newArr1);
            byte[] fileArr = AES.convertToSingleByteArr(newArr2);
         //   System.out.println(Arrays.toString(fileArr));
            int padding = 0xff & fileArr[fileArr.length - 1];
          //  System.out.println("padding" + padding);
            byte[] newFileArr = Arrays.copyOfRange(fileArr, 0, (fileArr.length - padding));
            writeByte(Encryptor.cbcDecrypt(newFileArr), endFile);
//AES CTR Encryption
        } else if (answer == 2) {
            byte[] ctrEncryption = Encryptor.ctrEncrypt((byte) new SecureRandom().generateSeed(1)[0], (arr));
            while (key.length() != 16) {
                System.out.println("What is your AES key?It must be 16 chars: ");
                key = scanner.nextLine();
            }
            // System.out.println(Arrays.toString(arr));
            byte[] newArr = AES.padByteArr(ctrEncryption);
           // System.out.println(Arrays.toString(newArr));
            byte[][][] newArr1 = AES.convertToKeyStates(newArr);
            byte[][][] newArr2 = AES.AESEncrypt(key, newArr1);
            // System.out.println(newArr2.length + " "+newArr2[0].length+" "+newArr2[0][0].length);
            byte[] fileArr = AES.convertToSingleByteArr(newArr2);
            writeByte(fileArr, endFile);
            //IV = scanner.nextLine();
           // writeByte(Encryptor.cbcDecrypt(arr), endFile);
            //AES CTR decryption
        } else if (answer == 3) {

            while (key.length() != 16) {
                System.out.println("What is your AES key? It must be 16 char: ");
                key = scanner.nextLine();
            }
            byte[][][] newArr1 = AES.convertToKeyStates(arr);
            byte[][][] newArr2 = AES.AESDecrypt(key, newArr1);
            byte[] fileArr = AES.convertToSingleByteArr(newArr2);
           // System.out.println(Arrays.toString(fileArr));
            int padding = 0xff & fileArr[fileArr.length - 1];
            //System.out.println("padding" + padding);
            byte[] newFileArr = Arrays.copyOfRange(fileArr, 0, (fileArr.length - padding));
            writeByte(Encryptor.ctrDecrypt(newFileArr), endFile);
        }else

            {
                System.out.println("Incorrect number");

            }
            System.out.println("Done");
        }

        // To write the bytes into a file
        static void writeByte ( byte[] bytes, File file){

            // Try block to check for exceptions
            try {

                // Initialize a pointer in file
                // using OutputStream
                OutputStream os = new FileOutputStream(file);

                // Starting writing the bytes in it
                os.write(bytes);

                // Display message onconsole for successful
                // execution
                System.out.println("Successfully"
                        + " byte inserted");

                // Close the file connections
                os.close();
            }

            // Catch block to handle the exceptions
            catch (Exception e) {

                // Display exception on console
                System.out.println("Exception: " + e);
            }
        }
    }
