package BOJ_4386;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        double[][] stars = new double[n][2];

        for (int i=0; i<n; i++) {
            String[] line = br.readLine().split(" ");
            stars[i][0] = Double.parseDouble(line[0]);
            stars[i][1] = Double.parseDouble(line[1]);
        }

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                if (i != j)
                    edges.add(new Edge(i, j, Math.sqrt(Math.pow(stars[i][0] - stars[j][0], 2) + Math.pow(stars[i][1] - stars[j][1], 2))));
            }
        }

        int[] parent = new int[n];
        // init
        for (int i=0; i<n; i++)
            parent[i] = i;

        Collections.sort(edges, (e1, e2) -> (int) (e1.weight-e2.weight));

        int cnt = 0;
        double sum = 0;
        for (Edge edge : edges) {

            if (find(parent, edge.start) != find(parent, edge.end)) {
                union(parent, edge.start, edge.end);
                sum += edge.weight;

                if (++cnt == n-1)
                    break;
            }
        }

        System.out.println(String.format("%.2f", sum));
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
            parent[parentB] = parentA;
        else
            parent[parentA] = parentB;
    }

    static class Edge {

        int start;
        int end;

        double weight;

        Edge(int start, int end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public String toString() {
            return ""+start+"-"+end+": "+weight;
        }
    }
}
