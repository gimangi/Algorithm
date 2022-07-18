package BOJ_14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dy = { 1, -1, 0, 0 };
    static int[] dx = { 0, 0, 1, -1 };

    static int[][] dyCrs = { {0, -1, 0}, {0, 1, 0}, {-1, 0, 1}, {-1, 0, 1} };
    static int[][] dxCrs = { {-1, 0, 1}, {-1, 0, 1}, {0, -1, 0}, {0, 1, 0} };

    static int N, M;
    static int box[][];

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int max = 0;

        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        box = new int[N][M];

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++)
                box[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                ArrayList<Position> list = new ArrayList<>();
                list.add(new Position(i, j));
                max = Math.max(max, dfs(list, box[i][j]));

                for (int k=0; k<dyCrs.length; k++) {
                    int temp = box[i][j];
                    for (int u=0; u<dyCrs[k].length; u++) {
                        int nextY = i + dyCrs[k][u];
                        int nextX = j + dxCrs[k][u];

                        if (nextY >= 0 && nextY < N && nextX >= 0 && nextX < M)
                            temp += box[nextY][nextX];
                        else {
                            temp = -1;
                            break;
                        }
                    }
                    max = Math.max(temp, max);
                }
            }
        }
        System.out.println(max);

    }

    static int dfs(ArrayList<Position> visited, int cnt) {
        if (visited.size() == 4)
            return cnt;

        Position last = visited.get(visited.size()-1);
        int max = 0;
        outLoop:
        for (int i=0; i<dy.length; i++) {
            int nextY = last.y+dy[i];
            int nextX = last.x+dx[i];

            if (nextY >= 0 && nextY < N && nextX >= 0 && nextX < M) {
                for (int j=0; j<visited.size(); j++) {
                    if (visited.get(j).y == nextY && visited.get(j).x == nextX)
                        continue outLoop;
                }

                ArrayList<Position> newVisit = new ArrayList<>(visited);
                newVisit.add(new Position(nextY, nextX));
                max = Math.max(max, dfs(newVisit, cnt + box[nextY][nextX]));
            }
        }

        return max;
    }
}

class Position {
    int y;
    int x;
    Position(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
