package BOJ_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T, N, K;
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String[] line = br.readLine().split(" ");
            N = Integer.parseInt(line[0]);
            K = Integer.parseInt(line[1]);

            Node[] nodes = new Node[N + 1];

            line = br.readLine().split(" ");
            for (int i = 1; i <= N; i++)
                nodes[i] = new Node(i, Integer.parseInt(line[i - 1]));

            for (int i = 0; i < K; i++) {
                line = br.readLine().split(" ");
                int from = Integer.parseInt(line[0]);
                int to = Integer.parseInt(line[1]);
                nodes[from].link.add(nodes[to]);
                nodes[to].linked++;
            }
            int dest = Integer.parseInt(br.readLine());
            System.out.println(topologySort(nodes, dest));
        }

        br.close();
    }

    static int topologySort(final Node[] nodes, int dest) {
        final int N = nodes.length - 1;
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n2.cost - n1.cost);
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i] = nodes[i].cost;

            if (nodes[i].linked == 0)
                pq.offer(nodes[i]);
        }

        for (int i = 0; i < N; i++) {
            if (pq.isEmpty())
                break;

            Node cur = pq.poll();

            if (cur.num == dest)
                break;

            for (Node next : cur.link) {
                dp[next.num] = Math.max(dp[next.num], dp[cur.num] + next.cost);

                if (--next.linked == 0)
                    pq.offer(next);
            }
        }
        return dp[dest];
    }

    static class Node {
        int num;
        int cost;
        int linked = 0;
        ArrayList<Node> link = new ArrayList<>();
        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}
