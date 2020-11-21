package encoders;

public class RC4Encoder {

    public static void main(String[] args) {

    }

    public byte[] generateKey(String keyString, int n, int length) {
        int[] s = new int[n];
        byte[] k = new byte[n];
        char[] key = keyString.toCharArray();

        // 密钥调度算法KSA
        for (int i = 0; i < n; i++) {
            s[i] = i;
            k[i] = (byte) key[i % key.length];
        }
        for (int i = 0, j = 0; i < n; i++) {
            j = (j + s[i] + k[i]) % n;
            swap(s, i, j);
        }

        // 伪随机数生成算法PRGA
        int i = 0, j = 0, l = 0, t;
        byte[] res = new byte[length];
        while (l < length) {
            i = (i+1) % n;
            j = (j + s[i]) % n;
            swap(s, i, j);
            t = (s[i] + s[j]) % n;
            res[l] = (byte) s[t];
            l++;
        }
        return res;
    }

    public char[] encode(byte[] key, String plaintextString) {
        char[] res = new char[plaintextString.length()];
        char[] plaintext = plaintextString.toCharArray();
        for (int i = 0; i < plaintextString.length(); i++) {
            // & 0xff 取低8位，java中按位运算的结果为int，并且将两个操作数先转为int再进行运算
            res[i] = (char) ((plaintext[i] & 0xff)^ (key[i] & 0xff));
        }
        return res;
    }

    public void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

}
