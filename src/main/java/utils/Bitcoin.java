package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Bitcoin {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 基于SHA-256的比特币采矿模拟实验
        // 消息摘要算法SHA256实例和Random实例初始化
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        Random random = new Random();

        short rand;
        byte[] in;
        boolean res;
        long start, end;
        long[] time = new long[6];

        for (int i = 1; i <= 5; i++) {
            // 前导0个数分别为1-6的时间消耗
            for (int j = 1; j <= 3; j++) {
                // 进行三组测试，然后计算平均值
                start = System.currentTimeMillis();
                do {
                    // 随机生成一个32bits的整数
                    rand =
                            (short) random.nextInt(32767);
                    in = intToByte(rand);
                    // 进行SHA-256计算
                    in = md.digest(in);
                    md.reset();
                    res = judge(in, i);
                } while (!res);
                end = System.currentTimeMillis();
                System.out.println("第" + j +"组满足哈希值有"+i+"个前导0的整数为:"+rand);
                System.out.println("其哈希值为:");
                printByte(in);
                // 总时间求和
                time[i] += end - start;
                System.out.println("消耗时间:" + (end - start) + "ms");
            }
            System.out.println("---------------------------------------------------------");
            System.out.println("满足哈希值有"+i+"个前导0的十组时间的平均值为："+ time[i] / 10 + "ms");
            System.out.println("---------------------------------------------------------");
        }

        for (int i = 1; i < 6; i++) {
            System.out.println("---------------------------------------------------------");
            System.out.println("满足哈希值有"+i+"个前导0的十组时间的平均值为："+ time[i] / 3 + "ms");
        }
    }

    public static byte[] intToByte(int in) {
        // 8bits整数转字节数组
        byte[] res = new byte[2];
        for (int i = 0; i < 2; i++) {
            res[i] = (byte) (in & 0xff);
            in >>= 8;
        }
        return res;
    }

    public static boolean judge(byte[] seq, int num) {
        // 判断前导字符串的前num个是否均为0，是则返回true，代表挖矿成功，否则返回false
        // 0010 0000 & 1000 0000 == 0 （代表第1位为0）
        // 这里可以优化，只要 0010 0000 < 0x8f (1000 0000)即代表第一个为0
        int aux = 0x80, count = num;
        for (int i = 0; i <= num/8; i++) {
            for (int j = 0; j < 8; j++) {
                if (((seq[i] & 0xff) & (aux & 0xff)) != 0) {
                    return false;
                }
                count--;
                if (count == 0) {
                    return true;
                }
                aux >>= 1;
            }
            // 重置aux，以进行下一个8bits判断
            aux = 0x80;
        }
        return true;
    }

    public static void printByte(byte[] in) {
        // 打印字节数组，用于打印哈希值的二进制
        int aux;
        System.out.print("[");
        for (int i = 0; i < in.length; i++) {
            aux = in[i] & 0xff;
            for (int j = 7; j >= 0; j--) {
                int tmp = (aux & 0xff) & 0x80;
                System.out.print(tmp >> 7);
                aux <<= 1;
            }
        }
        System.out.println("]");
    }
}
