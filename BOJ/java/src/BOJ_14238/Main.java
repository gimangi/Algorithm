package BOJ_14238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static char[] seq;
    static boolean[][][][][] dp;
    static int S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        S = str.length();
        int[] freq = new int[3];
        seq = new char[S + 1];
        dp = new boolean[S + 1][S + 1][S + 1][3][3];

        for (int i = 0; i < str.length(); i++)
            freq[str.charAt(i) - 'A']++;

        if (solution(freq[0], freq[1], freq[2], 0, 0))
            System.out.println(seq);
        else
            System.out.println(-1);
    }

    static boolean solution(int a, int b, int c, int yd2, int yd1) {
        if (a == 0 && b == 0 && c == 0) return true;

        if (dp[a][b][c][yd2][yd1]) return false;
        dp[a][b][c][yd2][yd1] = true;

        if (a > 0) {
            seq[S - a - b - c] = 'A';
            if (solution( a - 1, b, c, yd1, A)) return true;
        }
        if (b > 0 && yd1 != B) {
            seq[S - a - b - c] = 'B';
            if (solution(a, b - 1, c, yd1, B)) return true;
        }
        if (c > 0 && yd2 != C && yd1 != C) {
            seq[S - a - b - c] = 'C';
            if (solution(a, b, c - 1, yd1, C)) return true;
        }

        return false;
    }

    static final int A = 0;
    static final int B = 1;
    static final int C = 2;

}
