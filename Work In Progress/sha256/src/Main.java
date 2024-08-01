import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        byte[] arr = new byte[10];
        System.out.println(Arrays.deepToString(shaHash.preProcess(arr, 1600)));
        System.out.println(shaHash.preProcess(arr, 1600)[0].length);

String redblock = "hello world";
byte[] redblockarr = shaHash.asciiConversion(redblock);
byte[][] redblockarr1 = shaHash.preProcess(redblockarr,redblockarr.length*8);
System.out.println("YO" + Arrays.deepToString(redblockarr1));
System.out.println("What good " + Arrays.deepToString(shaHash.prepareMessageSchedule(redblockarr1)));
byte[][] bob = shaHash.prepareMessageSchedule(redblockarr1);
System.out.println(bob.length);

String hi  = "";
for(int i = 0; i < redblockarr1.length;i++){
    hi+=bytesToHex(bob[i]);
}
System.out.println("YOYOYO "+ hi);
System.out.println();
System.out.println("HEELO " + Arrays.toString(shaHash.asciiConversion(redblock)));




        String string1 = "01101000";
        String string2 = "01111111";
        String string3 = "00011000";

        String lol = "01101111";
        String lol1 = "00100000";
        String lol2 = "01110111";
        String lol3 = "01101111";
        byte[] arr1k = new byte[4];
        arr1k[0] = (byte) Integer.parseInt(lol, 2);
        arr1k[1] = (byte) Integer.parseInt(lol1, 2);
        arr1k[2] = (byte) Integer.parseInt(lol2, 2);
        arr1k[3] = (byte) Integer.parseInt(lol3, 2);
        System.out.println("Whatgood" + Arrays.toString(shaHash.sigma(arr1k,17,19,10)));

      // byte[][] bobby = shaHash.sigma(arr1k,7,18,3);
        System.out.println();

        byte[] arr1 = new byte[3];
        arr1[0] = (byte) Integer.parseInt(string1, 2);
        arr1[1] = (byte) Integer.parseInt(string2, 2);
        arr1[2] = (byte) Integer.parseInt(string3, 2);
        System.out.println(Arrays.toString(shaHash.rotation(arr1,23)));
        System.out.println(((byte) 128) & 0xFF);
        System.out.println(arr1[0]);
        System.out.println(Integer.toBinaryString(arr1[0]));
        System.out.println(Integer.toBinaryString(arr1[1]));
        System.out.println(Integer.toBinaryString(arr1[2]));

        System.out.println(Integer.toBinaryString(((byte)(52))));
        System.out.println(Integer.parseInt("00110100",2));
        System.out.println(arr1[0]);
        System.out.println(Integer.toBinaryString(arr1[0]));
        System.out.println(Integer.parseInt("1101000",2));

    }
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}