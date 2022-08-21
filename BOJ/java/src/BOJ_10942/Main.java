package BOJ_10942;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M;
        N = Integer.parseInt(br.readLine());
        int[] seq = new int[N + 1];
        String[] line = br.readLine().split(" ");
        for (int i = 1; i <= N; i++)
            seq[i] = Integer.parseInt(line[i - 1]);
        boolean[][] dp = new boolean[N + 1][N + 1];
        solution(seq, dp, N);

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            line = br.readLine().split(" ");
            int S = Integer.parseInt(line[0]);
            int E = Integer.parseInt(line[1]);
            if (dp[S][E])
                sb.append("1\n");
            else
                sb.append("0\n");
        }
        System.out.println(sb);
    }

    static void solution(final int[] seq, final boolean[][] dp, int N) {
        for (int i = 1; i <= N; i++)
            dp[i][i] = true;
        for (int i = 1; i <= N - 1; i++)
            if (seq[i] == seq[i + 1])
                dp[i][i + 1] = true;
        for (int i = 2; i <= N - 1; i++) {
            for (int j = 1; i + j <= N; j++) {
                if (seq[j] == seq[j + i] && dp[j + 1][j + i - 1])
                    dp[j][j + i] = true;
            }
        }
    }
}
