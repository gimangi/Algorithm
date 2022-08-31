package BOJ_1766;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        Node[] nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++)
            nodes[i] = new Node(i);

        for (int i = 0; i < M; i++) {
            line = br.readLine().split(" ");
            int first = Integer.parseInt(line[0]);
            int second = Integer.parseInt(line[1]);
            nodes[first].link.add(nodes[second]);
            nodes[second].linked++;
        }

        System.out.println(topologySort(nodes));
    }

    static StringBuilder topologySort(final Node[] nodes) {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.num - n2.num);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (nodes[i].linked == 0)
                pq.offer(nodes[i]);
        }

        for (int i = 0; i < N; i++) {
            Node cur = pq.poll();
            sb.append(cur.num + " ");

            for (Node next : cur.link) {
                if (--next.linked == 0)
                    pq.offer(next);
            }
        }
        return sb;
    }

    static class Node {
        int num;
        int linked = 0;
        ArrayList<Node> link = new ArrayList<>();
        Node(int num) {
            this.num = num;
        }
    }
}
