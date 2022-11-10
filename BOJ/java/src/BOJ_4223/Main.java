package BOJ_4223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    static final long[] base = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());

            if (p == 0 || a == 0) break;


            if (isFermat(p, a) && !isPrime(p, a))
                System.out.println("yes");
            else
                System.out.println("no");
        }
    }

    static boolean isFermat(long n, long a) {
        BigInteger t = BigInteger.valueOf(a).modPow(BigInteger.valueOf(n), BigInteger.valueOf(n));

        if (t.compareTo(BigInteger.valueOf(a)) == 0)
            return true;

        return false;
    }

    static boolean isPrime(long n, long a) {
        boolean isPrime = true;
        for (int t=0; t<base.length; t++) {
            if (base[t] >= n)
                break;

            if (!isTestPassed(n, base[t])) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    static boolean isTestPassed(long n, long a) {
        long m = n - 1;
        int k = 0;
        while (m % 2 == 0) {
            m = m >> 1;
            k += 1;
        }
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

}
