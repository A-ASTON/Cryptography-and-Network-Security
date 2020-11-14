package encoders;

public class CaesarEncoder implements Encoder {

    @Override
    public String encode(String plaintext, String key) {
        return caesarEncode(plaintext, Integer.parseInt(key));
    }

    @Override
    public String getName() {
        return "Casear Cipher Encoder";
    }

    private String caesarEncode(String plaintext, int k) {
        StringBuilder sb = new StringBuilder();
        char cipher;

        // 区分大小写，并且只对字母进行加密
        for (int i = 0; i < plaintext.length(); i++) {
            char cur = plaintext.charAt(i);
            cipher = cur;

            //输入为大写
            if (cur >= 65 && cur <= 91) {
                cipher = (char)((cur - 65 + k) % 26 + 65);
            }

            //输入为小写
            if (cur >= 97 && cur <= 123) {
                cipher = (char)((cur - 97 + k) % 26 + 97);
            }

            sb.append(cipher);
        }
        return sb.toString();
    }

    public CaesarEncoder() {

    }
}
