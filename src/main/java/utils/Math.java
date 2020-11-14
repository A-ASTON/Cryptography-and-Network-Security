package utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Math {
    public static int pow(int a, int b, int p) {
        // 快速幂算法求a^b，注意！！！大整数会溢出！
        // 当指数太大时，int和long都无法存储结果
        // 所以对中间结果先取余，利用(a * b) % p = ((a % p) * (b % p)) % p
        int res = 1;
        while (b != 0) {
            if ((b & 1) == 1) {
                res = ((res % p) * (a % p)) % p;
            }
            a = (a * a) % p;
            b >>= 1;
        }
        return res;
    }

    public static int gcd(int a, int b) {
        // 辗转相除法求最大公约数
        if (a < b) {
            return gcd(b, a);
        }
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a%b);
        }

    }

    public static boolean isRelativePrim(int a, int b) {
        if (gcd(a, b) == 1) {
            // 如果两个数的最大公约数为1，则这两个数互为素数
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPrim(int a) {
        // 判断一个数是否为素数
        for (int i = a - 1; i > 1; i--) {
            if (a % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int euler(int num) {
        // 返回给定数字的欧拉函数返回值
        if (isPrim(num)) {
            // 如果给定数字为素数，根据欧拉函数定理，直接返回num - 1
            return num - 1;
        }
        int count = 0;
        for (int i = 1; i < num; i++) {
            if (isRelativePrim(num, i)) {
                count++;
            }
        }
        return count;
    }

    private static void clear(int[] array) {
        // 数组清0
        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
    }

    public static ArrayList<Integer> primitiveRoot(int num) {
        // 返回给定数字的本原根
        int euler_num = euler(num);
        // 存储结果
        ArrayList<Integer> res = new ArrayList<>();
        // 辅助数组，判断重复
        int[] aux = new int[num];
        // a = 2, 3, 4, ..., euler_num
        for (int a = 2; a <= num; a++) {
            // 判断内循环是否遍历完而没有提前break，从而判断是否为本原根
            int flag = 0;
            // aux数组清0，复用
            clear(aux);
            // 2^1, 2^2, ..., 2^euler(num) mod n 均不相同，且均与n互为素数
            for (int i = 1; i <= euler_num; i++) {
                int remain = pow(a, i, num);
                if (isRelativePrim(num, remain)) {
                    // 余数和给定数字n互为素数
                    if (aux[remain] != 0) {
                        flag = 1;
                        break;
                    } else {
                        // aux[remain] 置 1， 代表该余数出现过
                        aux[remain] = 1;
                    }
                } else {
                    // 不互余，即不为本原根
                    flag = 1;
                    break;
                }
            }
            // 标志位没被置1，即当前循环的a为本原根
            if (flag == 0) {
                res.add(a);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("25的本原根有:" + Arrays.toString(primitiveRoot(25).toArray()));
    }
}
