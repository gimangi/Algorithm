package BOJ_9328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int ALPHABETS = 26;

    final static int[] dx = { -1, 1, 0, 0 };
    final static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t;
        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String[] inputs = br.readLine().split(" ");
            int h = Integer.parseInt(inputs[0]);
            int w = Integer.parseInt(inputs[1]);

            char[][] map = new char[h + 2][w + 2];
            boolean[] haveKey = new boolean[ALPHABETS];

            // 테두리 초기화
            for (char[] row : map) {
                Arrays.fill(row, '.');
            }

            // 지도 입력
            for (int i = 1; i <= h; i++) {
                String line = br.readLine();
                for (int j = 1; j <= w; j++) {
                    map[i][j] = line.charAt(j - 1);
                }
            }

            String keyInput = br.readLine();
            for (int i = 0; i < keyInput.length(); i++) {
                if (keyInput.charAt(i) == '0') break;
                haveKey[keyInput.charAt(i) - 'a'] = true;
            }

            System.out.println(bfs(h, w, map, haveKey));

        }
    }

    static int bfs(int h, int w, char[][] map, boolean[] haveKey) {
        int count = 0;
        boolean[][] visited = new boolean[h + 2][w + 2];
        List<Pos>[] lockedDoors = new ArrayList[ALPHABETS];

        // lockedDoors 초기화
        for (int i = 0; i < lockedDoors.length; i++) {
            lockedDoors[i] = new ArrayList<>();
        }

        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Pos p = queue.poll();

            for (int i = 0; i < dy.length; i++) {
                int nextY = p.y + dy[i];
                int nextX = p.x + dx[i];

                if (nextX < 0 || nextY < 0 || nextX >= w + 2 || nextY >= h + 2) continue;
                char look = map[nextY][nextX];

                if (look == '*' || visited[nextY][nextX]) continue;

                // 열쇠
                if ('a' <= look && look <= 'z') {
                    haveKey[look - 'a'] = true;

                    for (Pos door : lockedDoors[look - 'a']) {
                        queue.offer(new Pos(door.y, door.x));
                    }

                    lockedDoors[look - 'a'].clear();
                }
                // 문
                else if ('A' <= look && look <= 'Z') {
                    if (!haveKey[look - 'A']) {
                        // 재방문을 기약하며..
                        lockedDoors[look - 'A'].add(new Pos(p.y, p.x));
                        continue;
                    }
                }
                else if (look == '$') {
                    count++;
                }

                queue.offer(new Pos(nextY, nextX));
                visited[nextY][nextX] = true;
            }
        }


        return count;
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
