package BOJ_2887;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] parent = new int[N];
        Planet[] planets = new Planet[N];

        for (int i=0; i<N; i++) {
            String[] line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            int z = Integer.parseInt(line[2]);
            planets[i] = new Planet(i, x, y, z);
            parent[i] = i;
        }

        ArrayList<Edge> edges = new ArrayList<>();
        Arrays.sort(planets, (p1, p2) -> p1.x-p2.x);
        for (int i=1; i<N; i++)
            edges.add(new Edge(planets[i-1].id, planets[i].id, planets[i].x-planets[i-1].x));
        Arrays.sort(planets, (p1, p2) -> p1.y-p2.y);
        for (int i=1; i<N; i++)
            edges.add(new Edge(planets[i-1].id, planets[i].id, planets[i].y-planets[i-1].y));
        Arrays.sort(planets, (p1, p2) -> p1.z-p2.z);
        for (int i=1; i<N; i++)
            edges.add(new Edge(planets[i-1].id, planets[i].id, planets[i].z-planets[i-1].z));

        Collections.sort(edges, (e1, e2) -> e1.weight-e2.weight);

        long sum = 0;
        int cnt = 0;

        for (Edge edge : edges) {
            if (find(parent, edge.start) != find(parent, edge.end)) {
                union(parent, edge.start, edge.end);
                sum += edge.weight;

                if (++cnt == N-1)
                    break;
            }
        }
        System.out.println(sum);
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

    static class Planet {

        int id;
        int x;
        int y;
        int z;
        Planet(int id, int x, int y, int z) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

}
