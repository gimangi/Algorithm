package BOJ_7562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            boolean visited[][] = new boolean[N][N];

            StringTokenizer st = new StringTokenizer(br.readLine());

            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            Queue<Position> q = new LinkedList<>();
            Position start = new Position(startX, startY);
            Position end = new Position(endX, endY);

            q.offer(start);
            visited[startX][startY] = true;
            int cnt = 0;

            outLoop:
            while (!q.isEmpty()) {
                int qSize = q.size();

                for (int j=0; j<qSize; j++) {
                    Position p = q.poll();
                    if (p.x == end.x && p.y == end.y) {

                        break outLoop;
                    }

                    for (Position next: p.getNextDirs(N)) {

                        if (!visited[next.x][next.y]) {
                            visited[next.x][next.y] = true;
                            q.offer(next);
                        }
                    }
                }
                cnt++;
            }

            System.out.println(cnt);

        }

    }
}

class Position {
    int x;
    int y;
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    ArrayList<Position> getNextDirs(int N) {
        ArrayList<Position> res = new ArrayList<>();
        if (x-2>=0 && y-1>=0)
            res.add(new Position(x-2, y-1));
        if (x-1>=0 && y-2>=0)
            res.add(new Position(x-1, y-2));
        if (x+1<N && y-2>=0)
            res.add(new Position(x+1, y-2));
        if (x+2<N && y-1>=0)
            res.add(new Position(x+2, y-1));
        if (x-2>=0 && y+1<N)
            res.add(new Position(x-2, y+1));
        if (x-1>=0 && y+2<N)
            res.add(new Position(x-1, y+2));
        if (x+1<N && y+2<N)
            res.add(new Position(x+1, y+2));
        if (x+2<N && y+1<N)
            res.add(new Position(x+2, y+1));
        return res;
    }
}
