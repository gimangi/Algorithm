package BOJ_2252;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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

        System.out.println(topologicalSort(nodes));
        br.close();
    }

    static StringBuilder topologicalSort(final Node[] nodes) {
        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (nodes[i].linked == 0)
                queue.offer(nodes[i]);
        }

        for (int i = 0; i < N; i++) {
            Node cur = queue.poll();
            sb.append(cur.num + " ");

            for (Node next : nodes[cur.num].link) {
                if (--next.linked == 0)
                    queue.add(next);
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
