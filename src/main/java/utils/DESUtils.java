package utils;

import java.util.*;

public class DESUtils {
    public static int[] ipTable = {58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7};
    public static int[] invIpTable = {40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25};
    public static int[] pTable = {16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25};
    public static int[] eTable = {32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1};
    public static int[][][] sTable = {
            {
                    {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
                    {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
                    {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
                    {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } },
            {
                    {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
                    {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
                    {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
                    {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } },
            {
                    {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
                    {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
                    {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
                    {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } },
            {
                    {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
                    {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
                    {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
                    {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } },
            {
                    {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
                    {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
                    {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
                    {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } },
            {
                    {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
                    {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
                    {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
                    {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } },
            {
                    {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
                    {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
                    {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
                    {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } },
            {
                    {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
                    {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
                    {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
                    {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } }
    };
    public static int[] PC_1 = { 57, 49, 41, 33, 25, 17, 9, 1, 58,
            50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45,
            37, 29, 21, 13, 5, 28, 20, 12, 4 }; // 表13：64->56

    public static int[] PC_2 = { 14, 17, 11, 24, 1, 5, 3, 28, 15,
            6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37,
            47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36,
            29, 32 }; // 表14：56->48

    public static int[] LeftMove = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2,
            2, 2, 2, 2, 1 }; // 表15：左移位置列表

    public static void main(String[] args) {
        byte[] res = DESUtils.stringToBits("Aszu");
        System.out.println(Arrays.toString(res));
    }
    

    public static byte[] stringToBits(String plaintext) {
        // 双端队列代替栈
        Deque<Byte> tmp = new ArrayDeque();
        byte[] res = new byte[plaintext.length() * 8];

        for (int i = 0; i < plaintext.length(); i++) {
            int flag = 0;
            char cur = plaintext.charAt(i);
//            System.out.println("source:" + Integer.toBinaryString(cur));
            while (cur != 0 || flag < 8) {
                tmp.addFirst((byte) (cur & 1));
                cur >>= 1;
                flag++;
            }
            flag = 0;
//            System.out.println("res:");
            while (!tmp.isEmpty()) {
                res[i * 8 + flag] = tmp.removeFirst();
//                System.out.print(res[i * 8 + flag]);
                flag++;
            }
//            System.out.println();
        }
        return res;

    }

    public static void print(byte[] in, int rowLen) {
        // 用于打印矩阵
        int len = in.length;
        for (int i = 0; i < len; i++) {
            if ((i+1) % rowLen  == 0) {
                System.out.println(in[i]);
            } else {
                System.out.print(in[i] + ",");
            }
        }
        System.out.println();
    }

    public static byte[] transposition(byte[] plaintext,int[] table) {
        //根据传入的table进行初始逆置换或者是P盒置换、E盒、密钥的PC盒置换
        // 完成各种置换
        int len = table.length;
        byte[] res = new byte[len];
        for (int i = 0; i < len; i++) {
            res[i] = plaintext[table[i] - 1];
        }
        return res;
    }

    public static void LRChange(byte[] plaintext) {
        for (int i = 0 ; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                // 0 - 4、 1 - 5、 2 - 6、 3 - 7......交换
                byte tmp = plaintext[i * 8 + j + 4];
                plaintext[i * 8 + j + 4] = plaintext[i * 8 + j];
                plaintext[i * 8 + j] = tmp;
            }
        }
    }

    public static byte[] chooseExtend(byte[] right) {
        byte[] res = new byte[48];
        for (int i = 0; i < 48; i++) {
            res[i] = right[eTable[i] - 1];
        }
        return res;
    }

    public static byte[] chooseCompress(byte[] right) {
        int row;
        int col;
        byte tmp;
        byte[] res = new byte[32];
        for (int i = 0; i < 8; i++) {
            row = (right[i * 6 + 0] << 1) + right[i * 6 + 5];
            col = (right[i * 6 + 1] << 3) + (right[i * 6 + 2] << 2) + (right[i * 6 + 3] << 1) + right[i * 6 + 4];
            tmp = (byte)sTable[i][row][col];
            for (int j = 3; j >= 0; j--) {
                res[i * 4 + j] = (byte) (tmp & 1);
                tmp >>= 1;
            }
        }
        return res;
    }

    public static byte[][] createSubKey(byte[] key) {
        // 用传入的key生成16个长度为48位的子密钥
        byte[][] subKeys = new byte[16][48];

        // 置换选择一 64->56
        byte[] tmp = transposition(key, PC_1);

        // 分成C和D（前28和后28）
        byte[] C = Arrays.copyOfRange(tmp,0, 28);
        byte[] D = Arrays.copyOfRange(tmp,28, 56);

        // 存放每次C和D组合成的新的56位密钥，以进行下一步的置换选择
        byte[] tmpKey = new byte[56];

        // 循环移位并合并然后置换生成子密钥
        for (int i = 0; i < 16; i++) {
            rotateShift(C, LeftMove[i]);
            rotateShift(D, LeftMove[i]);
            System.arraycopy(C, 0, tmpKey, 0, 28);
            System.arraycopy(D, 0, tmpKey, 28, 28);

            // 得到56位后进行置换选择2，得到该轮的密钥
            subKeys[i] = transposition(tmpKey, PC_2);
        }
        return subKeys;
    }

    public static void rotateShift(byte key[], int move) {
        // 建立临时数组，大小为move，即循环移位将向左移出的部分
        byte[] tmp = new byte[move];
        for (int i = 0; i < move; i++) {
            tmp[i] = key[i];
        }

        for (int i = 0; i < key.length - move; i++) {
            // 左移
            key[i] = key[i + move];
        }

        // 移出的元素补到最后，相当于完成循环左移
        for (int i = 0; i < move; i++) {
            key[i + key.length - move] = tmp[i];
        }
    }
}
