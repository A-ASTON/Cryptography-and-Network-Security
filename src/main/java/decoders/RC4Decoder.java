package decoders;

public class RC4Decoder {
    public char[] decode(char[] ciphertext, char[] key) {
        char[] res = new char[ciphertext.length];
        for (char i = 0; i < ciphertext.length; i++) {
            res[i] =
                    (char) (ciphertext[i] ^ key[i]);
        }
        return res;
    }
}
