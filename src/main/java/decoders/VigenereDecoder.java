package decoders;

import java.util.Scanner;

public class VigenereDecoder implements Decoder {
    @Override
    public String decode(String ciphertext, String key) {
        // 密钥预处理，转换为ASCII的数组
        char[] keys = key.toLowerCase().toCharArray();
        int keysLen = keys.length;

        StringBuilder sb = new StringBuilder();
        char cipher;

        // plaintext明文长度
        int len = ciphertext.length();

        int count = 0;
        for (int i = 0; i < len; i++) {
            char cur = ciphertext.charAt(i);
            cipher = cur;

            //输入为大写
            if (cur >= 65 && cur <= 91) {
                cipher = (char)((cur - 65 - keys[(i - count) % keysLen] - 'a' + 26) % 26 + 65);
            } else if (cur >= 97 && cur <= 123) {
                // 输入为小写
                cipher = (char)((cur - 97 - keys[(i - count) % keysLen] - 'a' + 26) % 26 + 97);
            } else {
                count++;
            }
            sb.append(cipher);
        }
        return sb.toString();
    }

    public String decode(String ciphertext, Integer[] keys) {
        int keysLen = keys.length;

        StringBuilder sb = new StringBuilder();
        char cipher;

        // plaintext明文长度
        int len = ciphertext.length();

        int count = 0;
        for (int i = 0; i < len; i++) {
            char cur = ciphertext.charAt(i);
            cipher = cur;

            //输入为大写
            if (cur >= 65 && cur <= 91) {
                cipher = (char)((cur - 65 - keys[(i - count) % keysLen] + 26) % 26 + 65);
            } else if (cur >= 97 && cur <= 123) {
                // 输入为小写
                cipher = (char)((cur - 97 - keys[(i - count) % keysLen] + 26) % 26 + 97);
            } else {
                count++;
            }

            sb.append(cipher);
        }
        return sb.toString();
    }

    @Override
    public String getName() {
        return "Vigenere Cipher Decoder";
    }
}
