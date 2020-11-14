package decoders;

import encoders.DESEncoder;
import utils.DESUtils;

public class DESDecoder {

    public static void main(String[] args) {

}
    public byte[] decode(byte[] plaintext, byte[] key) {
        // 初始置换
        plaintext = DESUtils.transposition(plaintext, DESUtils.ipTable);

        // 16轮加密
        // 生成16个子密钥
        byte[][] subKeys = DESUtils.createSubKey(key);

        // des加密和解密中不同的部分在这里
        decodeCore(plaintext, subKeys);

        // 左右交换
        DESUtils.LRChange(plaintext);

        plaintext = DESUtils.transposition(plaintext, DESUtils.invIpTable);
        return plaintext;
    }

    private void decodeCore(byte[] plaintext, byte[][] key) {
        // 用原有的plaintext分成L和R两个BitSet
        byte[] left = new byte[32];
        byte[] right = new byte[32];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                left[i * 4 + j] = plaintext[i * 8 + j];
                right[i * 4 + j] = plaintext[i * 8 + j + 4];
            }
        }

        // 16轮解密
        for (int i = 15; i >= 0; i--) {
            // 选择扩展运算 E盒
            byte[] Eout = DESUtils.chooseExtend(right);

            // 明文和密钥异或
            for (int j = 0; j < 48; j++) {
                Eout[j] = (byte) (Eout[j] ^ key[i][j]);
            }

            // 选择压缩运算S盒
            byte[] Sout = DESUtils.chooseCompress(Eout);

            // 置换运算P盒
            Sout = DESUtils.transposition(Sout, DESUtils.pTable);

            // 新的right作为下一轮的right，旧的right作为下一轮的left
            byte[] tmp = right.clone();

            // L 和 R进行异或运算
            for (int j = 0; j < 32; j++) {
                right[j] = (byte) (left[j] ^ Sout[j]);
            }

            left = tmp;
        }

        // 最后将L和R合并还原为64位
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                plaintext[i * 8 + j] = left[i * 4 + j];
                plaintext[i * 8 + j + 4] = right[i * 4 + j];
            }
        }
    }
}
