package BOJ_12869;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] hp = new int[3];
        String[] line = br.readLine().split(" ");
        for (int i = 0; i < N; i++)
            hp[i] = Integer.parseInt(line[i]);

        dp = new int[61][61][61];
        for (int i = 0; i < dp.length; i++)
            for (int j = 0; j < dp[i].length; j++)
                for (int k = 0; k < dp[j].length; k++)
                    dp[i][j][k] = Integer.MAX_VALUE;
        dp[0][0][0] = 0;

        System.out.println(solution(hp[0], hp[1], hp[2]));
    }

    static int solution(int hp1, int hp2, int hp3) {
        hp1 = Math.max(hp1, 0);
        hp2 = Math.max(hp2, 0);
        hp3 = Math.max(hp3, 0);

        if (dp[hp1][hp2][hp3] != Integer.MAX_VALUE)
            return dp[hp1][hp2][hp3];
        if (dp[hp1][hp3][hp2] != Integer.MAX_VALUE)
            return dp[hp1][hp2][hp3] = dp[hp1][hp3][hp2];
        if (dp[hp2][hp1][hp3] != Integer.MAX_VALUE)
            return dp[hp1][hp2][hp3] = dp[hp2][hp1][hp3];
        if (dp[hp2][hp3][hp1] != Integer.MAX_VALUE)
            return dp[hp1][hp2][hp3] = dp[hp2][hp3][hp1];
        if (dp[hp3][hp1][hp2] != Integer.MAX_VALUE)
            return dp[hp1][hp2][hp3] = dp[hp3][hp1][hp2];
        if (dp[hp3][hp2][hp1] != Integer.MAX_VALUE)
            return dp[hp1][hp2][hp3] = dp[hp3][hp2][hp1];

        dp[hp1][hp2][hp3] = Math.min(dp[hp1][hp2][hp3], solution(hp1 - 9, hp2 - 3, hp3 - 1) + 1);
        dp[hp1][hp2][hp3] = Math.min(dp[hp1][hp2][hp3], solution(hp1 - 9, hp2 - 1, hp3 - 3) + 1);
        dp[hp1][hp2][hp3] = Math.min(dp[hp1][hp2][hp3], solution(hp1 - 3, hp2 - 9, hp3 - 1) + 1);
        dp[hp1][hp2][hp3] = Math.min(dp[hp1][hp2][hp3], solution(hp1 - 3, hp2 - 1, hp3 - 9) + 1);
        dp[hp1][hp2][hp3] = Math.min(dp[hp1][hp2][hp3], solution(hp1 - 1, hp2 - 9, hp3 - 3) + 1);
        dp[hp1][hp2][hp3] = Math.min(dp[hp1][hp2][hp3], solution(hp1 - 1, hp2 - 3, hp3 - 9) + 1);

        return dp[hp1][hp2][hp3];
    }
}
