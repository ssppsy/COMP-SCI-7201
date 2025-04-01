import sys

def karatsuba(x, y):
    """Karatsuba 乘法算法"""
    if x < 10 or y < 10:
        return x * y

    n = max(len(str(x)), len(str(y)))
    m = n // 2

    high1, low1 = divmod(x, 10**m)
    high2, low2 = divmod(y, 10**m)

    z0 = karatsuba(low1, low2)
    z1 = karatsuba((low1 + high1), (low2 + high2))
    z2 = karatsuba(high1, high2)

    return z2 * 10**(2 * m) + (z1 - z2 - z0) * 10**m + z0

def to_base(n, base):
    """将整数 n 转换为 base 进制字符串"""
    if n == 0:
        return "0"
    digits = []
    while n:
        digits.append(str(n % base))
        n //= base
    return "".join(digits[::-1])

def main():
    # 读取输入
    I1, I2, B = sys.stdin.readline().split()
    B = int(B)

    # 转换为 B 进制整数
    num1 = int(I1, B)
    num2 = int(I2, B)

    # 学校方法的整数加法
    sum_result = num1 + num2

    # Karatsuba 乘法
    product_result = karatsuba(num1, num2)

    # 整数除法
    division_result = num1 // num2

    # 转换回 B 进制并输出
    print(to_base(sum_result, B), to_base(product_result, B), to_base(division_result, B))

if __name__ == "__main__":
    main()

