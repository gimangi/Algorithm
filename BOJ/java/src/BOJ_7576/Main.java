package BOJ_7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int dn[] = { 0, 0, 1, -1, 0 , 0 };
    static final int dm[] = { 0, 0, 0, 0, 1, -1 };

    static int M, N;

    static int box[][];
    static boolean visited[][];

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        box = new int[N][M];
        visited = new boolean[N][M];
        int remain = 0;


        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < M; c++) {
                box[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Position> q = new LinkedList<>();

        for (int j=0; j<N; j++) {
            for (int k=0; k<M; k++) {
                if (box[j][k] == 1) {
                    q.offer(new Position(j, k));
                    visited[j][k] = true;
                } else if (box[j][k] == 0)
                    remain++;
            }
        }


        int day = 0;

        outLoop:
        while (!q.isEmpty()) {
            Position next = q.poll();

            if (remain == 0)
                break outLoop;

            for (int j=0; j<dm.length; j++) {
                Position look = new Position( next.y+dn[j], next.z+dm[j]);
                look.days = next.days + 1;
                day = Math.max(day, look.days);

                if (look.y>=0 && look.y<N && look.z>=0 && look.z<M) {
                    if (!visited[look.y][look.z] && box[look.y][look.z] != -1) {
                        visited[look.y][look.z] = true;
                        if (box[look.y][look.z] == 0) {
                            box[look.y][look.z] = 1;
                            remain--;
                        }
                        q.offer(look);
                    }
                }
            }
        }

        if (remain > 0)
            day = -1;

        System.out.println(day);


    }


    static class Position {

        int y;
        int z;

        int days = 0;

        Position(int y, int z) {
            this.y = y;
            this.z = z;
        }
    }

}
