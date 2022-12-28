package BOJ_9328_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 실패작!@!@!
 */
public class Main {

    static int h, w;
    static char[][] map;
    static boolean[] haveKey;
    static List<Pos>[] keyIdx;

    final static int[] dx = { -1, 1, 0, 0 };
    final static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t;

        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String[] inputs = br.readLine().split(" ");
            h = Integer.parseInt(inputs[0]);
            w = Integer.parseInt(inputs[1]);

            map = new char[h][w];
            keyIdx = new ArrayList[26];
            for (int i = 0; i < keyIdx.length; i++) {
                keyIdx[i] = new ArrayList<>();
            }

            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = line.charAt(j);
                    if ('a' <= map[i][j] && map[i][j] <= 'z') {
                        keyIdx[map[i][j] - 'a'].add(new Pos(i, j));
                    }
                }
            }

            haveKey = new boolean[26];

            String keyInput = br.readLine();
            for (int i = 0; i < keyInput.length(); i++) {
                if (keyInput.charAt(i) == '0') break;

                haveKey[keyInput.charAt(i) - 'a'] = true;
            }

            System.out.println(solution());
        }

    }

    static int solution() {

        int count = 0;

        // $ 위치에서 탐색 시작
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {

                if (map[i][j] == '$') {
                    boolean[][] visited = new boolean[h][w];

                    if (dfs(i, j, visited)) count++;
//                    else System.out.println(i + ", " + j);
                }
            }
        }

        return count;
    }

    static boolean dfs(int y, int x, boolean[][] visited) {
        return dfs(y, x, visited, null);
    }

    // jumped : 재방문하면 사이클이 생기는 점들
    static boolean dfs(int y, int x, boolean[][] visited, List<Pos> jumped) {
        if (jumped != null) {
            for (Pos pos : jumped) {
                if (y == pos.y && x == pos.x) return false;
            }
        }

        visited[y][x] = true;

        if (map[y][x] == '*') return false;

        // 문에 막혔을 때
        if ('A' <= map[y][x] && map[y][x] <= 'Z') {
            if (!jump(y, x, jumped)) return false;
        }

        boolean ret = false;
        for (int i = 0; i < dx.length; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];

            if (0 <= nextX && nextX < w && 0 <= nextY && nextY < h) {
                if (!visited[nextY][nextX]) {
                    ret = ret || dfs(nextY, nextX, visited, jumped);
                }
            }
            // 밖으로 이어질 때
            else {
                return true;
            }
        }
        return ret;
    }

    static boolean jump(int y, int x, List<Pos> stop) {
        // 열쇠를 가지고 있는지 먼저 확인
        if (haveKey[map[y][x] - 'A']) {
            return true;
        }

        if (stop == null) {
            stop = new ArrayList<>();
        }
        stop.add(new Pos(y, x));

        // 문을 열기 위한 열쇠 위치들
        for (Pos idx : keyIdx[map[y][x] - 'A']) {
            boolean[][] keyVisit = new boolean[h][w];

            // 열쇠 위치에서 탐색 시작
            if (dfs(idx.y, idx.x, keyVisit, stop)) {
                haveKey[map[y][x] - 'A'] = true;
                return true;
            }
        }
        return false;
    }


    static class Pos {
        final int y;
        final int x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}
