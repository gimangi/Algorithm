package BOJ_16928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N, M;
        String line[] = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        ArrayList<Integer> link[] = new ArrayList[101];
        HashMap<Integer, Integer> jumpMap = new HashMap<>();
        boolean visited[] = new boolean[101];

        for (int i=1; i<=100; i++) {
            link[i] = new ArrayList<>();
            for (int j=1; j<=6; j++)
                if (i+j <= 100)
                    link[i].add(i+j);
        }

        for (int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            jumpMap.put(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }

        for (int i=0; i<M; i++) {
            line = br.readLine().split(" ");
            jumpMap.put(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }

        Queue<CellInfo> q = new LinkedList<>();
        q.offer(new CellInfo(1));
        visited[1] = true;

        int ans = 0;

        while (!q.isEmpty()) {
            CellInfo c = q.poll();

            if (c.num == 100) {
                ans = c.cnt;
                break;
            }

            for (int i=0; i<link[c.num].size(); i++) {
                int next = link[c.num].get(i);
                if (!visited[next]) {
                    Integer jump = jumpMap.get(next);
                    if (jump != null) {
                        visited[next] = true;
                        next = jump;
                    }

                    CellInfo nextC = new CellInfo(next);
                    nextC.cnt = c.cnt + 1;
                    q.offer(nextC);
                    visited[next] = true;
                }
            }
        }
        System.out.println(ans);

    }

}

class CellInfo {
    int num;
    int cnt = 0;

    CellInfo(int num) {
        this.num = num;
    }
}
