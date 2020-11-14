package encoders;

import java.util.Scanner;

public class VigenereEncoder implements Encoder {
    @Override
    public String encode(String plaintext, String key) {
        // 密钥预处理，转换为ASCII的数组
        char[] keys = key.toLowerCase().toCharArray();
        int keysLen = keys.length;

        StringBuilder sb = new StringBuilder();
        char cipher;

        // plaintext明文长度
        int len = plaintext.length();

        // 非字母个数，用于减去，使得明文能对应上其用于加密的密钥
        int count = 0;
        for (int i = 0; i < len; i++) {
            char cur = plaintext.charAt(i);
            cipher = cur;

            //输入为大写
            if (cur >= 65 && cur <= 91) {
                cipher = (char)((cur - 65 + keys[(i - count) % keysLen] - 'a') % 26 + 65);
            } else if (cur >= 97 && cur <= 123) {
                //输入为小写
                cipher = (char)((cur - 97 + keys[(i - count) % keysLen] - 'a') % 26 + 97);
            } else {
                count++;
            }

            sb.append(cipher);
        }
        return sb.toString();
    }

    public String encode(String plaintext, Integer[] keys) {
        int keysLen = keys.length;

        StringBuilder sb = new StringBuilder();
        char cipher;

        // plaintext明文长度
        int len = plaintext.length();

        // 非字母个数，用于减去，使得明文能对应上其用于加密的密钥
        int count = 0;
        for (int i = 0; i < len; i++) {
            char cur = plaintext.charAt(i);
            cipher = cur;

            //输入为大写
            if (cur >= 65 && cur <= 91) {
                cipher = (char)((cur - 65 + keys[(i - count) % keysLen]) % 26 + 65);
            } else if (cur >= 97 && cur <= 123) {
                // 输入为小写
                cipher = (char)((cur - 97 + keys[(i - count) % keysLen]) % 26 + 97);
            } else {
                // 输入为非字母
                count++;
            }
            sb.append(cipher);
        }
        return sb.toString();
    }

    @Override
    public String getName() {
        return "Vigenere Cipher Encoder";
    }
}
