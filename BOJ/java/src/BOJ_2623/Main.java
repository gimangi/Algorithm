package BOJ_2623;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs;
        int N, M;

        inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        Node[] nodes = new Node[N + 1];
        // init
        for (int i = 0; i <= N; i++) {
            nodes[i] = new Node(i);
        }

        // read inputs
        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");

            for (int j = 1; j < inputs.length - 1; j++) {
                int cur = Integer.parseInt(inputs[j]);
                int next = Integer.parseInt(inputs[j + 1]);
                nodes[cur].linking.add(nodes[next]);
                nodes[next].inDegree++;
            }
        }

        List<Node> sorted = topologySort(nodes);
        if (sorted == null)
            System.out.println(0);
        else
            sorted.forEach(n -> {
                System.out.println(n.num);
            });
    }

    private static List<Node> topologySort(Node[] nodes) {
        Queue<Node> queue = new LinkedList<>();
        List<Node> result = new ArrayList<>();

        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i].inDegree == 0) {
                queue.offer(nodes[i]);
            }
        }

        // for n times (exclude index 0)
        for (int i = 1; i < nodes.length; i++) {
            if (queue.isEmpty()) {
                return null;
            }

            Node cur = queue.poll();
            for (Node link : cur.linking) {
                link.inDegree--;
                if (link.inDegree == 0) {
                    queue.offer(link);
                }
            }

            result.add(cur);
        }

        return result;
    }

    static class Node {
        final int num;
        int inDegree = 0;
        List<Node> linking = new ArrayList<>();

        public Node(int num) {
            this.num = num;
        }
    }

}
