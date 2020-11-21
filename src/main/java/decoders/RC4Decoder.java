package decoders;

public class RC4Decoder {
    public char[] decode(char[] ciphertext, byte[] key) {
        char[] res = new char[ciphertext.length];
        for (char i = 0; i < ciphertext.length; i++) {
            res[i] = (char) ((ciphertext[i] & 0xff) ^ (key[i] & 0xff));
        }
        return res;
    }
}
