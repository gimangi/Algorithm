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
        int[][] placedBlack = new int[N][N];
        int[][] placedWhite = new int[N][N];
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
            ans += dfs(posBlack, placedBlack, 0, 0);
        if (!posWhite.isEmpty())
            ans += dfs(posWhite, placedWhite, 0, 0);

        System.out.println(ans);
    }

    static int dfs(final ArrayList<Position> positions, final int[][] placed, int idx, int cnt) {
        if (!(idx < positions.size())) return cnt;
        int ret = 0;

        Position cur = positions.get(idx);
        if (placed[cur.y][cur.x] == 0) {
            addPlace(placed, cur.y, cur.x, 1);
            ret = Math.max(dfs(positions, placed, idx + 1, cnt + 1), ret);
            addPlace(placed, cur.y, cur.x, -1);
        }

        ret = Math.max(dfs(positions, placed, idx + 1, cnt), ret);
        return ret;
    }

    static void addPlace(final int[][] placed, int y, int x, int num) {
        placed[y][x] += num;
        for (int i = 1; y - i >= 0 && x + i < N; i++)
            placed[y - i][x + i] += num;
        for (int i = 1; y - i >= 0 && x - i >= 0; i++)
            placed[y - i][x - i] += num;
        for (int i = 1; y + i < N && x - i >= 0; i++)
            placed[y + i][x - i] += num;
        for (int i = 1; y + i < N && x + i < N; i++)
            placed[y + i][x + i] += num;
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
