package BOJ_17404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n;
    static int[][] cost;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cost = new int[n][3];

        for (int i = 0; i < n; i++) {
            String[] inputs = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        System.out.println(solution());

    }

    static int solution() {
        int pickRed = search(0);
        int pickGreen = search(1);
        int pickBlue = search(2);

        return Math.min(Math.min(pickBlue, pickGreen), pickRed);
    }

    static int search(int startColor) {
        int[][] dp = new int[n][3];
        for (int i = 0; i < 3; i++) {
            if (i == startColor)
                dp[0][i] = cost[0][i];
            else
                dp[0][i] = 2_000_000;
        }

        for (int i = 1; i < n; i++) {
            dp[i][0] = cost[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]); // red
            dp[i][1] = cost[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]); // green
            dp[i][2] = cost[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]); // blue
        }

        for (int i = 0; i < 3; i++) {
            if (i == startColor)
                dp[n - 1][i] = Integer.MAX_VALUE;
        }

        return Math.min(Math.min(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
    }
}
