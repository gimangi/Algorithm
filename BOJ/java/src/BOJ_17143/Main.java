package BOJ_17143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    static int r, c, m;
    static Shark[][] map;
    static LinkedList<Shark> sharks;

    static final int[] dy = { 0, -1, 1, 0, 0 };
    static final int[] dx = { 0, 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        r = Integer.parseInt(inputs[0]);
        c = Integer.parseInt(inputs[1]);
        m = Integer.parseInt(inputs[2]);

        map = new Shark[r + 1][c + 1];
        sharks = new LinkedList<>();

        while (m-- > 0) {
            inputs = br.readLine().split(" ");
            int y = Integer.parseInt(inputs[0]);
            int x = Integer.parseInt(inputs[1]);

            int speed = Integer.parseInt(inputs[2]);
            int dir = Integer.parseInt(inputs[3]);
            int size = Integer.parseInt(inputs[4]);

            Shark shark = new Shark(speed, dir, size);
            shark.y = y;
            shark.x = x;

            sharks.add(shark);
            map[y][x] = shark;
        }

        System.out.println(solution());

    }

    static int solution() {
        int angler = 1;
        int sum = 0;

        for (; angler <= c; angler++) {
            // 상어 잡기
            for (int i = 1; i <= r; i++) {
                if (map[i][angler] != null) {
                    sum += map[i][angler].size;
                    sharks.remove(map[i][angler]);
                    map[i][angler] = null;

                    break;
                }
            }

            // 상어 이동
            moveSharks();
        }

        return sum;
    }

    static void moveSharks() {

        // 모든 상어 이동
        for (Shark shark : sharks) {
            moveShark(shark);
        }

        Shark[][] newMap = new Shark[r + 1][c + 1];
        List<Shark> gc = new ArrayList<>();

        for (Shark shark : sharks) {
            // 이미 상어가 있으면 크기가 큰 녀석이 잡아먹는다.
            if (newMap[shark.y][shark.x] != null) {
                Shark big = bigShark(newMap[shark.y][shark.x], shark);

                if (big == shark) gc.add(newMap[shark.y][shark.x]);
                else gc.add(shark);

                newMap[shark.y][shark.x] = big;
            } else {
                newMap[shark.y][shark.x] = shark;
            }
        }

        for (Shark rm : gc) {
            sharks.remove(rm);
        }

        map = newMap;
    }

    static void moveShark(Shark shark) {

        int step = shark.speed;
        if (shark.dir == 1 || shark.dir == 2) {
            step %= (r - 1) * 2;
        }
        else {
            step %= (c - 1) * 2;
        }

        for (int i = 0; i < step; i++) {
            int ny = shark.y + dy[shark.dir];
            int nx = shark.x + dx[shark.dir];

            if (ny < 1 || ny > r || nx < 1 || nx > c) {
                shark.dir = opposite(shark.dir);
                ny = shark.y + dy[shark.dir];
                nx = shark.x + dx[shark.dir];
            }
            shark.y = ny;
            shark.x = nx;
        }
    }

    static Shark bigShark(Shark s1, Shark s2) {
        if (s1.size < s2.size) return s2;
        return s1;
    }

    static int opposite(int dir) {
        if (dir == 1) return 2;
        else if (dir == 2) return 1;
        else if (dir == 3) return 4;
        else return 3;
    }


    static class Shark {
        int y;
        int x;
        int dir;

        final int speed;
        final int size;

        public Shark(int speed, int dir, int size) {
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }
}
