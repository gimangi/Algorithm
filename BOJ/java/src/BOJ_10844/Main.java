package BOJ_10844;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final long MOD = 1000000000;

    static Long dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new Long[101][10];

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1l;
        }

        long result = 0;

        for (int i = 1; i <= 9; i++)
            result = (result + (recur(N, i) % MOD)) % MOD;

        System.out.println(result);

    }

    static long recur(int len, int num) {
        if (len == 1)
            return dp[len][num];

        if (dp[len][num] == null) {
            if (num == 0)
                dp[len][num] = recur(len - 1, 1);
            else if (num == 9)
                dp[len][num] = recur(len - 1, 8);
            else {
                dp[len][num] = recur(len - 1, num - 1) % MOD + recur(len - 1, num + 1) % MOD;
                dp[len][num] %= MOD;
            }
        }
        return dp[len][num];
    }

}
