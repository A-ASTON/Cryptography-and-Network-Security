package utils;

import java.math.BigInteger;
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

    public static int inverse(int p, int a) {
        if (a <= 0 || a >= p) {
            // a只能是模p的乘法群
            return 0;
        }
        int[] x = new int[]{1, 0, p};
        int[] y = new int[]{0, 1, a};
        int q;
        while (y[2] != 0 && y[2] != 1) {
            q = x[2] / y[2];
            int tmp = y[2];
            y[2] = x[2] % y[2];
            x[2] = tmp;

            int tmp1 = y[0];
            int tmp2 = y[1];
            y[0] = x[0] - q*y[0];
            y[1] = x[1] - q*y[1];
            x[0] = tmp1;
            x[1] = tmp2;
        }
        if (y[2] == 1) {
            return y[1];
        } else {
            return 0;
        }
    }

    private static int x;
    private static int y;
    public static int inverse_recursion(int p, int a) {
        // 递归版扩展欧几里得求逆元,由于java的基本类型没有地址或者引用，所以采用类中设置成员变量以实现
        // 求p和a的gcd的同时，求a关于1modp的逆元，结果存放在x中
        // 并且调用完后, ,需要将x、y重新赋值
        if (a == 0) {
            x = 1;
            y = 0;
            return p;
        } else {
            // 递归结束点为：x = 1, y = 0，此时a就是gcd(p, a),由于执行了这么多次的递归函数，所以x和y就相应地计算了这么多次
            // 最终x存放了a的逆元
            int res = inverse_recursion(a, p%a);
            int t = y;
            y = x - p/a * y;
            x = t;
            return res;
        }
    }

    public static void main(String[] args) {
//        System.out.println("25的本原根有:" + Arrays.toString(primitiveRoot(25).toArray()));
        for (int i = 1; i < 71; i++) {
            x = 1;
            y = 0;
            inverse_recursion(71,i);
            // 由于逆元的取值范围只能为 1 - 70，所以负数要求取一次模
            System.out.println(i + "关于1mod71的逆元为（递归）:"+ (y + 71) % 71);

            int r2 = inverse(71,i);
            System.out.println(i + "关于1mod71的逆元为:"+ (r2 + 71) % 71);

            BigInteger b = new BigInteger(String.valueOf(71));
            BigInteger a = new BigInteger(String.valueOf(i));
            System.out.println("使用大数库的逆元运算验证:" + a + "^-1 = " + a.modInverse(b));
            System.out.println("---------------------------------------------------");
        }



    }

}
