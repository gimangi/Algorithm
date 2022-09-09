package BOJ_10422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    final static long MAX = 1000000007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int L = Integer.parseInt(br.readLine());
            long[] dp = new long[L + 1];
            dp[0] = 1;

            for (int i = 2; i <= L; i += 2) {
                for (int j = 0; j <= i - 2; j++) {
                    dp[i] += (dp[j] * dp[i - j - 2]) % MAX;
                    dp[i] %= MAX;
                }
            }

            System.out.println(dp[L]);
        }
    }
}
