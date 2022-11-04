package BOJ_1965;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] box;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        box = new int[n];
        dp = new int[n];
        String[] spt = br.readLine().split(" ");

        for (int i = 0; i < n; i++)
            box[i] = Integer.parseInt(spt[i]);

        int max = 1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++)
                if (box[j] < box[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }

}
