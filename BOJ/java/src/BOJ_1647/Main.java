package BOJ_1647;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M;
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        ArrayList<Edge> edges = new ArrayList<>();
        int[] parents = new int[N+1];

        for (int i=0; i<M; i++) {
            line = br.readLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);

            edges.add(new Edge(start, end, weight));
        }

        for (int i=1; i<=N; i++)
            parents[i] = i;

        Collections.sort(edges, (e1, e2) -> e1.weight - e2.weight);
        int cnt = 0;
        int sum = 0;
        for (Edge edge : edges) {
            if (find(parents, edge.start) != find(parents, edge.end)) {
                union(parents, edge.start, edge.end);
                sum += edge.weight;

                if (++cnt == N - 2)
                    break;
            }
        }
        System.out.println(sum);

    }

    static void union(int[] parents, int a, int b) {
        int parentA = find(parents, a);
        int parentB = find(parents, b);

        if (parentA < parentB)
            parents[parentB] = parentA;
        else
            parents[parentA] = parentB;
    }

    static int find(int[] parents, int i) {
        if (parents[i] == i)
            return i;
        return find(parents, parents[i]);
    }

    static class Edge {
        int start;
        int end;
        int weight;
        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
