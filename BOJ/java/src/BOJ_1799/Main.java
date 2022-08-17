package BOJ_1799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N;

    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        int[] diagDown = new int[2 * N - 1];
        int[] diagUp = new int[2 * N - 1];
        ArrayList<Position> posBlack = new ArrayList<>();
        ArrayList<Position> posWhite = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) == 1;
                if (map[i][j]) {
                    if ((i + j) % 2 == 0)
                        posBlack.add(new Position(i, j));
                    else
                        posWhite.add(new Position(i, j));
                }
            }
        }
        int ans = 0;
        if (!posBlack.isEmpty())
            ans += dfs(posBlack, diagUp, diagDown, 0, 0);
        if (!posWhite.isEmpty())
            ans += dfs(posWhite, diagUp, diagDown, 0, 0);

        System.out.println(ans);
    }

    static int dfs(final ArrayList<Position> positions, final int[] diagUp, final int[] diagDown, int idx, int cnt) {
        if (!(idx < positions.size())) return cnt;
        int ret = 0;

        Position cur = positions.get(idx);
        int diagUpIdx = cur.y - cur.x + N - 1;
        int diagDownIdx = cur.y + cur.x;

        if (diagUp[diagUpIdx] == 0 && diagDown[diagDownIdx] == 0) {
            diagUp[diagUpIdx] += 1;
            diagDown[diagDownIdx] += 1;
            ret = Math.max(dfs(positions, diagUp, diagDown, idx + 1, cnt + 1), ret);
            diagUp[diagUpIdx] -= 1;
            diagDown[diagDownIdx] -= 1;
        }

        ret = Math.max(dfs(positions, diagUp, diagDown, idx + 1, cnt), ret);
        return ret;
    }

    static class Position {
        int y;
        int x;
        Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}
