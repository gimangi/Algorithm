package BOJ_16946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static int n, m;
    // 1 -> true / 0 - > false
    static boolean[][] map;

    static boolean[][] visited;

    // 연속된 0의 개수 저장
    static int[][] blankCount;
    // 연속된 0을 그룹으로 묶어서 자신이 속한 id를 저장
    static int[][] blankGroupId;
    static int groupCounter = 1;

    static int[][] result;

    final static int[] dy = { -1, 1, 0, 0 };
    final static int[] dx = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        n = Integer.parseInt(inputs[0]);
        m = Integer.parseInt(inputs[1]);

        map = new boolean[n][m];
        visited = new boolean[n][m];
        blankCount = new int[n][m];
        result = new int[n][m];
        blankGroupId = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) == '1';
            }
        }

        solution();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(result[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void solution() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!map[i][j] && !visited[i][j])
                    bfs(i, j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j]) {
                    countBlock(i, j);
                }
            }
        }
    }

    // 1인 위치들에서 인접한 0인 위치들 계산하여 result 에 저장
    static void countBlock(int r, int c) {
        result[r][c] = 1;

        List<Integer> visitedGroup = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int nr = r + dy[i];
            int nc = c + dx[i];

            if (nr < 0 || nr >= n || nc < 0 || nc >= m)
                continue;

            if (visitedGroup.contains(blankGroupId[nr][nc]))
                continue;

            result[r][c] += blankCount[nr][nc];
            visitedGroup.add(blankGroupId[nr][nc]);
        }
        result[r][c] %= 10;
    }

    // 0인 위치들끼리 bfs 하여 count 에 이어진 개수 저장
    static void bfs(int r, int c) {
        Queue<Pos> q = new LinkedList<>();
        List<Pos> visiting = new ArrayList<>();
        q.offer(new Pos(r, c));

        while (!q.isEmpty()) {
            Pos pos = q.poll();
            if (visited[pos.y][pos.x]) continue;

            visiting.add(pos);
            visited[pos.y][pos.x] = true;

            for (int i = 0; i < 4; i++) {
                int nr = pos.y + dy[i];
                int nc = pos.x + dx[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (map[nr][nc]) continue;

                q.offer(new Pos(nr, nc));
            }
        }

        for (Pos pos : visiting) {
            blankCount[pos.y][pos.x] = visiting.size();
            blankGroupId[pos.y][pos.x] = groupCounter;
        }
        groupCounter++;
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
