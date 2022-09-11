package BOJ_12969;

import java.util.Scanner;

public class Main {

    static int N, K;
    static char[] seq;
    static boolean[][][][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        seq = new char[N + 1];
        dp = new boolean[N + 1][N + 1][N + 1][N * (N - 1) / 2 + 1];

        if (solution(0, 0, 0, 0))
            System.out.println(seq);
        else
            System.out.println(-1);
    }

    static boolean solution(int len, int a, int b, int pairs) {
        if (len == N) {
            if (pairs == K)
                return true;
            return false;
        }

        if (dp[len][a][b][pairs])
            return false;
        dp[len][a][b][pairs] = true;

        seq[len] = 'A';
        if (solution(len + 1, a + 1, b, pairs)) return true;
        seq[len] = 'B';
        if (solution(len + 1, a, b + 1, pairs + a)) return true;
        seq[len] = 'C';
        if (solution(len + 1, a, b, pairs + a + b)) return true;

        return false;
    }

}
