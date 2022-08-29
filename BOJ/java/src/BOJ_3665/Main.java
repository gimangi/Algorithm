package BOJ_3665;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T, N, M;
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            Node[] nodes = new Node[N + 1];
            for (int i = 1; i <= N; i++)
                nodes[i] = new Node(i);

            String[] line = br.readLine().split(" ");
            ArrayList<Node> temp = new ArrayList<>();
            // nodes init
            for (int i = N - 1; i >= 0; i--) {
                int num = Integer.parseInt(line[i]);
                nodes[num].link.addAll(temp);
                nodes[num].linked = N - (temp.size() + 1);
                temp.add(nodes[num]);
            }

            M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                line = br.readLine().split(" ");
                int first = Integer.parseInt(line[0]);
                int second = Integer.parseInt(line[1]);
                if (nodes[first].link.contains(nodes[second])) {
                    nodes[first].link.remove(nodes[second]);
                    nodes[second].linked--;
                    nodes[second].link.add(nodes[first]);
                    nodes[first].linked++;
                } else {
                    nodes[second].link.remove(nodes[first]);
                    nodes[first].linked--;
                    nodes[first].link.add(nodes[second]);
                    nodes[second].linked++;
                }
            }
            System.out.println(topologySort(nodes, N));
        }
        br.close();
    }

    static StringBuilder topologySort(final Node[] nodes, int N) {
        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (nodes[i].linked == 0)
                queue.offer(nodes[i]);
        }

        for (int i = 0; i < N; i++) {
            if (queue.isEmpty())
                return new StringBuilder("IMPOSSIBLE");
            if (queue.size() > 1)
                return new StringBuilder("?");

            Node cur = queue.poll();
            sb.append(cur.num + " ");

            for (Node next : cur.link) {
                if (--next.linked == 0)
                    queue.offer(next);
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
