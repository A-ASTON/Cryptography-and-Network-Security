package decoders;

public class CaesarDecoder implements Decoder{
    @Override
    public String decode(String ciphertext, String key) {
        return caesarDecode(ciphertext, Integer.parseInt(key));
    }

    @Override
    public String getName() {
        return "Casear Cipher Decoder";
    }

    private String caesarDecode(String ciphertext, int key) {
        StringBuilder sb = new StringBuilder();
        char cipher;

        for (int i = 0; i < ciphertext.length(); i++) {
            char cur = ciphertext.charAt(i);
            cipher = cur;

            //输入为大写
            if (cur >= 65 && cur <= 91) {
                cipher = (char)((cur - 65 - key + 26) % 26 + 65);
            }

            //输入为小写
            if (cur >= 97 && cur <= 123) {
                cipher = (char)((cur - 97 - key + 26) % 26 + 97);
            }

            sb.append(cipher);
        }
        return sb.toString();
    }

    public CaesarDecoder() {

    }
}
