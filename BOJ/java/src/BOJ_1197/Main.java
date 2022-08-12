package BOJ_1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int V, E;
        String[] line = readLine();
        V = Integer.parseInt(line[0]);
        E = Integer.parseInt(line[1]);

        ArrayList<Edge> edges = new ArrayList<>();

        for (int i=0; i<E; i++) {
            line = readLine();
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);

            edges.add(new Edge(start, end, weight));
        }

        Collections.sort(edges, (e1, e2) -> e1.weight - e2.weight);
        int[] parent = new int[V+1];
        // parent init
        for (int i=0; i<V; i++)
            parent[i] = i;

        long sum = 0l;
        int cnt = 0;

        for (Edge edge : edges) {
            if (find(parent, edge.start) != find(parent, edge.end)) {
                union(parent, edge.start, edge.end);
                sum += edge.weight;

                if (++cnt == V-1)
                    break;
            }
        }

        System.out.println(sum);
    }

    static int find(int[] parent, int i) {
        if (parent[i] == i)
            return i;
        return find(parent, parent[i]);
    }

    static void union(int[] parent, int a, int b) {
        int parentA = find(parent, a);
        int parentB = find(parent, b);

        if (parentA < parentB)
            parent[parentA] = parentB;
        else
            parent[parentB] = parentA;
    }


    static String[] readLine() throws IOException {
        return br.readLine().split(" ");
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
