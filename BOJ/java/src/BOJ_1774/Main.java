package BOJ_1774;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N, M;
        int[] read = readNum();
        N = read[0]; M = read[1];

        // read ships
        int[][] ships = new int[N+1][2];
        for (int i=1; i<=N; i++) {
            read = readNum();
            ships[i][0] = read[0];
            ships[i][1] = read[1];
        }

        ArrayList<Edge> edges = new ArrayList<>();

        for (int i=1; i<=N; i++) {
            for (int j=i; j<=N; j++) {
                if (i != j)
                    edges.add(new Edge(i, j, getDistance(ships[i], ships[j])));
            }
        }

        for (int i=0; i<M; i++) {
            read = readNum();
            int start = read[0];
            int end = read[1];

            Edge edge = new Edge(start, end, getDistance(ships[start], ships[end]));
            edge.priority = 0;
            edges.add(edge);
        }

        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.priority != o2.priority)
                    return o1.priority-o2.priority;
                if (o1.weight>o2.weight)
                    return 1;
                else
                    return -1;
            }
        });

        int[] parent = new int[N+1];
        for (int i=1; i<=N; i++)
            parent[i] = i;

        int cnt = 0;
        double sum = 0;

        for (Edge edge : edges) {
            if (find(parent, edge.start) != find(parent, edge.end)) {
                union(parent, edge.start, edge.end);
                if (edge.priority != 0)
                    sum += edge.weight;

                if (++cnt == N-1)
                    break;
            }
        }

        System.out.println(String.format("%.2f", sum));
    }

    static void union(int[] parent, int a, int b) {
        int parentA = find(parent, a);
        int parentB = find(parent, b);

        if (parentA < parentB)
            parent[parentB] = parentA;
        else
            parent[parentA] = parentB;
    }

    static int find(int[] parent, int i) {
        if (parent[i] == i)
            return i;
        return find(parent, parent[i]);
    }

    static double getDistance(int[] shipA, int[] shipB) {
        return Math.sqrt(Math.pow(shipA[0]-shipB[0], 2) + Math.pow(shipA[1]-shipB[1], 2));
    }

    static class Edge {

        int priority = 1;

        int start;
        int end;
        double weight;

        Edge(int start, int end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public String toString() {
            return ""+start+"-"+end+": "+weight+" p="+priority;
        }
    }

    static int[] readNum() throws IOException {
        int[] ret = new int[2];
        String[] line = br.readLine().split(" ");
        ret[0] = Integer.parseInt(line[0]);
        ret[1] = Integer.parseInt(line[1]);

        return ret;
    }
}
