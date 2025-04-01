import java.math.BigInteger;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String I1 = scanner.next();
        String I2 = scanner.next();
        int B = scanner.nextInt();
        scanner.close();

        // 将输入转换为 B 进制的整数
        BigInteger num1 = new BigInteger(I1, B);
        BigInteger num2 = new BigInteger(I2, B);

        // 学校方法的整数加法
        BigInteger sum = num1.add(num2);

        // Karatsuba 乘法算法
        BigInteger product = karatsuba(num1, num2);

        // 整数除法
        BigInteger divisionResult = num1.divide(num2);

        // 转换回 B 进制并输出
        System.out.println(sum.toString(B) + " " + product.toString(B) + " " + divisionResult.toString(B));
    }

    // Karatsuba 乘法算法
    private static BigInteger karatsuba(BigInteger x, BigInteger y) {
        int N = Math.max(x.bitLength(), y.bitLength());
        if (N <= 2000) return x.multiply(y); // 当规模较小时，使用普通乘法

        N = (N / 2) + (N % 2); // 计算拆分点
        BigInteger high1 = x.shiftRight(N);
        BigInteger low1 = x.subtract(high1.shiftLeft(N));
        BigInteger high2 = y.shiftRight(N);
        BigInteger low2 = y.subtract(high2.shiftLeft(N));

        BigInteger z0 = karatsuba(low1, low2);
        BigInteger z1 = karatsuba(low1.add(high1), low2.add(high2));
        BigInteger z2 = karatsuba(high1, high2);

        return z2.shiftLeft(2 * N).add(z1.subtract(z2).subtract(z0).shiftLeft(N)).add(z0);
    }
}
