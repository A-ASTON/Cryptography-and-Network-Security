package encoders;

import decoders.RC4Decoder;

public class RC4Encoder {

    public static void main(String[] args) {

    }

    public char[] generateKey(String keyString, int n, int length) {
        int[] s = new int[n];
        byte[] k = new byte[n];
        char[] key = keyString.toCharArray();
        for (int i = 0; i < n; i++) {
            s[i] = i;
            k[i] = (byte) key[i % key.length];
        }
        for (int i = 0, j = 0; i < n; i++) {
            j = (j + s[i] + k[i]) % n;
            swap(s, i, j);
        }

        int i = 0, j = 0, l = 0, t;
        char[] res = new char[length];
        while (l < length) {
            i = (i+1) % n;
            j = (j + s[i]) % n;
            swap(s, i, j);
            t = (s[i] + s[j]) % n;
            res[l] = (char) s[t];
            l++;
        }
        return res;
    }

    public char[] encode(char[] key, String plaintextString) {
        char[] res = new char[plaintextString.length()];
        char[] plaintext = plaintextString.toCharArray();
        for (int i = 0; i < plaintextString.length(); i++) {
            res[i] = (char) (plaintext[i] ^ key[i]);
        }
        return res;
    }

    public void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

}
