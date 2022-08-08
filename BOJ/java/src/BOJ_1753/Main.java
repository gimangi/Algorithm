package BOJ_1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        int V, E;
        int first;

        String[] line = getLine();
        V = Integer.parseInt(line[0]);
        E = Integer.parseInt(line[1]);

        first = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<AdjEdge>> graph = new ArrayList<>();
        // init
        for (int i=0; i<=V; i++) {
            graph.add(new ArrayList<>());
        }

        // read
        for (int i=0; i<E; i++) {
            line = getLine();
            int lhs = Integer.parseInt(line[0]);
            int rhs = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);

            graph.get(lhs).add(new AdjEdge(rhs, weight));
        }

        // print
        int[] distance = dijkstra(V, graph, first);
        for (int i=1; i<=V; i++) {
            if (distance[i] == Integer.MAX_VALUE)
                System.out.println("INF");
            else
                System.out.println(distance[i]);
        }
    }

    public static String[] getLine() throws IOException {
        return br.readLine().split(" ");
    }

    public static int[] dijkstra(int V, ArrayList<ArrayList<AdjEdge>> graph, int first) {
        int[] distance = new int[V+1];

        for (int i=1; i<=V; i++)
            distance[i] = Integer.MAX_VALUE;
        distance[first] = 0;

        PriorityQueue<AdjEdge> pq = new PriorityQueue<>((v1, v2) -> v1.weight - v2.weight);
        pq.add(new AdjEdge(first, 0));

        while (!pq.isEmpty()) {
            AdjEdge cur = pq.poll();

            for (AdjEdge e : graph.get(cur.linked)) {
                if (distance[cur.linked] + e.weight < distance[e.linked]) {
                    distance[e.linked] = e.weight + distance[cur.linked];
                    pq.add(new AdjEdge(e.linked, distance[e.linked]));
                }
            }
        }
        return distance;
    }
}

class AdjEdge {
    int linked;
    int weight;

    AdjEdge(int linked, int weight) {
        this.linked = linked;
        this.weight = weight;
    }
}