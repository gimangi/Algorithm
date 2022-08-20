package BOJ_1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int R, C;
    static char[][] map;
    static boolean[] visited;

    final static int[] dr = { -1, 1, 0, 0 };
    final static int[] dc = { 0, 0, -1, 1 };

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        R = Integer.parseInt(line[0]);
        C = Integer.parseInt(line[1]);
        map = new char[R][C];
        visited = new boolean[26];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++)
                map[i][j] = str.charAt(j);
        }

        visited[map[0][0] - 'A'] = true;
        dfs(0, 0, 1);
        System.out.println(ans);
    }

    static void dfs(int r, int c, int cnt) {
        ans = Math.max(ans, cnt);
        for (int i = 0; i < 4; i++) {
            int nR = dr[i] + r;
            int nC = dc[i] + c;
            if (nR >= 0 && nR < R && nC >= 0 && nC < C) {
                int alphabet = map[nR][nC] - 'A';
                if (!visited[alphabet]) {
                    visited[alphabet] = true;
                    dfs(nR, nC, cnt + 1);
                    visited[alphabet] = false;
                }
            }
        }
    }
}
