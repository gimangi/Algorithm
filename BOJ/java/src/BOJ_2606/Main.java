package BOJ_2606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        boolean[] visited = new boolean[C+1];

        for (int i=0; i<=C; i++)
            graph.add(new ArrayList<>());

        for (int i=0; i<P; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            graph.get(c1).add(c2);
            graph.get(c2).add(c1);
        }

        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int next = q.poll();

            for (int look: graph.get(next)) {
                if (!visited[look]) {
                    visited[look] = true;
                    q.offer(look);
                    cnt++;
                }
            }

        }

        System.out.println(cnt);

    }
}
