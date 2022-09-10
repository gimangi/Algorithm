package BOJ_11066;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());
            int[] file = new int[K + 1];
            int[] prefixSum = new int[K + 1];
            int[][] dp = new int[K + 1][K + 1];

            int sum = 0;
            String[] line = br.readLine().split(" ");
            for (int i = 0; i < K; i++) {
                file[i + 1] = Integer.parseInt(line[i]);
                sum += file[i + 1];
                prefixSum[i + 1] = sum;
            }

            for (int i = K; i >= 1; i--) {
                for (int j = i; j <= K; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                    if (i == j)
                        dp[i][j] = 0;
                    else {
                        for (int k = i; k <= j - 1; k++) {
                            dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + getSum(prefixSum, i, k) + getSum(prefixSum, k + 1, j));
                        }
                    }
                }
            }

            System.out.println(dp[1][K]);
        }
    }

    static int getSum(int[] sum, int s, int e) {
        return sum[e] - sum[s - 1];
    }
}
