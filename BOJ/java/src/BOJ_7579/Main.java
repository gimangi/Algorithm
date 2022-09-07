package BOJ_7579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M;
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        int[] memory = new int[N + 1];
        int[] cost = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            memory[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            cost[i] = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][100 * N + 1];
        int ans = Integer.MAX_VALUE;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - cost[i] >= 0)
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cost[i]] + memory[i]);
                if (dp[i][j] >= M)
                    ans = Math.min(ans, j);
            }
        }
        System.out.println(ans);
        br.close();
    }
}
