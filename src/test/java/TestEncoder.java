import decoders.*;
import encoders.*;
import org.junit.Test;
import utils.DESUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TestEncoder {
    public static void main(String[] args) {
        // 输入明文
        System.out.println("Please input the plaintext:");
        Scanner scanner = new Scanner(System.in);
        String plaintext = scanner.nextLine();

        // 加密器和解密器工厂
        EncoderFactory encoderFactory = new EncoderFactory();
        DecoderFactory decoderFactory = new DecoderFactory();

        // 凯撒密码
        CaesarEncoder caesarEncoder = (CaesarEncoder) encoderFactory.create("Caesar");
        System.out.println("Using " + caesarEncoder.getName());

        String cipherText = caesarEncoder.encode(plaintext, "3");
        System.out.println("The ciphertext is:" + cipherText);


        CaesarDecoder caesarDecoder = (CaesarDecoder) decoderFactory.create("Caesar");
        System.out.println("Using " + caesarDecoder.getName());
        System.out.println("The plaintext is:" + caesarDecoder.decode(cipherText, "3"));
        System.out.println("------------------------");

        // 维吉尼亚密码
        VigenereEncoder vigenereEncoder = (VigenereEncoder) encoderFactory.create("vigenere");

        System.out.println("Please input the key:");
        String key = scanner.nextLine();
        ArrayList<Integer> keyList = new ArrayList<>();
        String[] list = key.split(" ");
        for (int i = 0; i < list.length; i++) {
            keyList.add(Integer.parseInt(list[i]));
        }
        Integer[] keys = keyList.toArray(new Integer[keyList.size()]);

        System.out.println("Using " + vigenereEncoder.getName());
        String ciphertext = vigenereEncoder.encode(plaintext, keys);
        System.out.println("The ciphertext is: " + ciphertext);

        VigenereDecoder vigenereDecoder = (VigenereDecoder) decoderFactory.create("vigenere");
        System.out.println("Using " + vigenereDecoder.getName());
        System.out.println("The plaintext is: " + vigenereDecoder.decode(ciphertext, keys));
    }

    @Test
    public void test() {
        System.out.println("test DES Encoder");
        DESEncoder desEncoder = new DESEncoder();
    }


    @Test
    public void testDES() {
        DESEncoder desEncoder = new DESEncoder();
        DESDecoder desDecoder = new DESDecoder();

        // 长度为8的明文
        byte[] test0 = DESUtils.stringToBits("ILoveSZU");
        // 长度为8的密钥
        byte[] testKey = DESUtils.stringToBits("SZUISOKA");
        System.out.println("需加密的明文：");
        DESUtils.print(test0, 8);
        byte[] secret = desEncoder.encode(test0, testKey);

        System.out.println("-----------------------------");
        System.out.println("加密得到的密文：");
        DESUtils.print(secret, 8);

        System.out.println("-----------------------------");
        byte[] res = desDecoder.decode(secret, testKey);
        System.out.println("解密密文得到的明文：");
        DESUtils.print(res, 8);
    }

    @Test
    public void testRC4() {
        RC4Encoder rc4Encoder = new RC4Encoder();
        RC4Decoder rc4Decoder = new RC4Decoder();

        String keyString = "abcde";
        String plaintext = "ShenZhen University";
        byte[] key = rc4Encoder.generateKey(keyString,256, plaintext.length());

        System.out.println("明文:" + plaintext);
        System.out.println("密钥:" + keyString);

        System.out.print("密钥流:");
        for (int i = 0; i < key.length; i++) {
            System.out.print((char) (key[i] & 0xff));
        }
        System.out.println();

        char[] ciphertext = rc4Encoder.encode(key, plaintext);
        System.out.println("密文:" + new String(ciphertext));

        char[] newPlaintext = rc4Decoder.decode(ciphertext, key);
        System.out.println("解密后明文:" + new String(newPlaintext));
    }
}