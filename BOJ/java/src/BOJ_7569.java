import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569 {

    static final int dh[] = { 1, -1, 0, 0, 0, 0 };
    static final int dn[] = { 0, 0, 1, -1, 0 , 0 };
    static final int dm[] = { 0, 0, 0, 0, 1, -1 };

    static int M, N, H;

    static int box[][][];
    static boolean visited[][][];

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M];
        visited = new boolean[H][N][M];

        for (int h=0; h<H; h++) {
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < M; c++) {
                    box[h][r][c] = Integer.parseInt(st.nextToken());
                }
            }
        }

        Queue<Position> q = new LinkedList<>();

        for (int i=0; i<H; i++) {
            for (int j=0; j<N; j++) {
                for (int k=0; k<M; k++) {
                    if (box[i][j][k] == 1) {
                        q.offer(new Position(i, j, k));
                        visited[i][j][k] = true;
                    }
                }
            }
        }

        int day = 0;

        outLoop:
        while (!q.isEmpty()) {

            Position next = q.poll();

            if (isRiped())
                break outLoop;

            for (int j=0; j<dh.length; j++) {
                Position look = new Position(next.x+dh[j], next.y+dn[j], next.z+dm[j]);
                look.days = next.days + 1;
                day = Math.max(day, look.days);

                if (look.x>=0 && look.x<H && look.y>=0 && look.y<N && look.z>=0 && look.z<M) {
                    if (!visited[look.x][look.y][look.z] && box[look.x][look.y][look.z] != -1) {
                        visited[look.x][look.y][look.z] = true;
                        box[look.x][look.y][look.z] = 1;
                        q.offer(look);
                    }
                }
            }


        }

        if (!isRiped())
            day = -1;

        System.out.println(day);


    }

    static boolean isRiped() {
        for (int i=0; i<H; i++)
            for (int j=0; j<N; j++)
                for (int k=0; k<M; k++)
                    if (box[i][j][k] == 0)
                        return false;
        return true;
    }

    static class Position {
        int x;
        int y;
        int z;

        int days = 0;

        Position(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

}
