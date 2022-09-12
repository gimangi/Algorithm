package BOJ_12996;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static long MAX = 1000000007;
    static long[][][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int S = Integer.parseInt(line[0]);
        int[] singer = new int[3];
        for (int i = 0; i < 3; i++)
            singer[i] = Integer.parseInt(line[i + 1]);
        dp = new long[51][51][51][51];
        for (int i = 0; i<dp.length; i++)
            for (int j = 0; j < dp[i].length; j++)
                for (int k = 0; k < dp[i][j].length; k++)
                    Arrays.fill(dp[i][j][k], -1);

        System.out.println(solution(S, singer[0], singer[1], singer[2]));
    }

    static long solution(int s, int a, int b, int c) {
        if (a < 0 || b < 0 || c < 0) return 0;
        if (s == 0) {
            if (a == 0 && b == 0 && c == 0)
                return 1;
            else
                return 0;
        }

        if (dp[s][a][b][c] != -1) return dp[s][a][b][c];
        long ret = 0;

        ret += solution(s - 1, a - 1, b, c);
        ret += solution(s - 1, a, b - 1, c);
        ret += solution(s - 1, a, b, c - 1);
        ret += solution(s - 1, a - 1, b - 1, c);
        ret += solution(s - 1, a - 1, b, c - 1);
        ret += solution(s - 1, a, b - 1, c - 1);
        ret += solution(s - 1, a - 1, b - 1, c - 1);

        return dp[s][a][b][c] = ret % MAX;
    }
}
