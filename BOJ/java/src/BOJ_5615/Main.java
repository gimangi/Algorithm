package BOJ_5615;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    static final long[] base = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int cnt = 0;
        for (int i=0; i<N; i++) {
            long s = Long.parseLong(br.readLine());
            long n = 2 * s + 1;

            if (n <= 4) {
                cnt += 1;
                continue;
            }
            boolean isPrime = true;
            for (int t=0; t<base.length; t++) {
                if (base[t] >= n)
                    break;

                if (!isPrime(n, base[t])) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                cnt += 1;
            }
        }

        System.out.println(cnt);
    }

    static boolean isPrime(long n, long a) {
        long m = n - 1;
        int k = 0;
        while (m % 2 == 0) {
            m = m >> 1;
            k += 1;
        }
//        BigInteger t = fastSquare(a, m, n);
        BigInteger t = BigInteger.valueOf(a).modPow(BigInteger.valueOf(m), BigInteger.valueOf(n));

        if (t.compareTo(BigInteger.ONE) == 0 || t.compareTo(BigInteger.valueOf(n - 1)) == 0)
            return true;

        BigInteger nBig = BigInteger.valueOf(n);
        for (int i = 1; i <= k - 1; i++) {
            t = t.multiply(t).mod(nBig);
            if (t.compareTo(BigInteger.ONE) == 0)
                return false;
            if (t.compareTo(BigInteger.valueOf(n - 1)) == 0)
                return true;
        }
        return false;
    }

    static BigInteger fastSquare(long a, long x, long n) {
        BigInteger y = BigInteger.ONE;
        BigInteger aBig = BigInteger.valueOf(a);
        BigInteger nBig = BigInteger.valueOf(n);

        while (x > 0) {
            if (x % 2 == 1)
                y = y.multiply(aBig).mod(nBig);
            aBig = aBig.multiply(aBig).mod(nBig);
            x = x >> 1;
        }
        return y;
    }
}