package BOJ_1854;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n, m, k;
        int[] line = getLine();
        n = line[0];
        m = line[1];
        k = line[2];

        ArrayList<ArrayList<AdjNode>> graph = new ArrayList<>();
        // init
        for (int i=0; i<=n; i++)
            graph.add(new ArrayList<>());

        // read
        for (int i=0; i<m; i++) {
            line = getLine();
            graph.get(line[0]).add(new AdjNode(line[1], line[2]));
        }

        // print
        int[] ans = dijkstra(n, graph, k);
        for (int i=1; i<=n; i++) {
            if (ans[i] == Integer.MAX_VALUE)
                System.out.println(-1);
            else
                System.out.println(ans[i]);
        }

    }

    public static int[] dijkstra(int n, ArrayList<ArrayList<AdjNode>> graph, int k) {
        PriorityQueue<Integer>[] distance = new PriorityQueue[n+1];

        // init
        for (int i=0; i<=n; i++) {
            distance[i] = new PriorityQueue<>(Collections.reverseOrder());
        }

        distance[1].add(0);

        PriorityQueue<AdjNode> pq = new PriorityQueue<>((v1, v2) -> v1.weight - v2.weight);
        pq.add(new AdjNode(1, 0));

        while (!pq.isEmpty()) {
            AdjNode cur = pq.poll();

            for (AdjNode node : graph.get(cur.vertex)) {
                if (distance[node.vertex].size() < k) {
                    distance[node.vertex].offer(cur.weight + node.weight);
                    pq.add(new AdjNode(node.vertex, cur.weight + node.weight));
                }
                else if (distance[node.vertex].peek() > cur.weight + node.weight) {
                    distance[node.vertex].poll();
                    distance[node.vertex].add(cur.weight + node.weight);
                    pq.add(new AdjNode(node.vertex, cur.weight + node.weight));
                }
            }
        }

        int[] ans = new int[n+1];
        for (int i=0; i<=n; i++) {
            if (distance[i].size() == k)
                ans[i] = distance[i].peek();
            else
                ans[i] = Integer.MAX_VALUE;
        }

        return ans;
    }

    public static int[] getLine() throws IOException {
        String[] line = br.readLine().split(" ");
        int[] ret = new int[3];
        for (int i=0; i<3; i++)
            ret[i] = Integer.parseInt(line[i]);
        return ret;
    }

    public static class AdjNode {
        int vertex;
        int weight;

        AdjNode(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

    }
}
