package BOJ_1707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        for (int t = 0; t < K; t++) {
            int V, E;
            String[] line = br.readLine().split(" ");
            V = Integer.parseInt(line[0]);
            E = Integer.parseInt(line[1]);
            Node[] nodes = new Node[V + 1];
            for (int i = 1; i <= V; i++)
                nodes[i] = new Node(i);

            for (int i = 0; i < E; i++) {
                line = br.readLine().split(" ");
                int from = Integer.parseInt(line[0]);
                int to = Integer.parseInt(line[1]);
                nodes[from].neighbor.add(nodes[to]);
                nodes[to].neighbor.add(nodes[from]);
            }

            Stack<NodeRequest> stack = new Stack<>();
            for (int i = 1; i <= V; i++) {
                stack.push(new NodeRequest(i, 1));
            }

            while (!stack.isEmpty()) {
                NodeRequest cur = stack.pop();
                if (nodes[cur.num].color != 0)
                    continue;

                nodes[cur.num].color = cur.colorRequest;
                int nextColor = cur.colorRequest == 1 ? 2 : 1;

                for (Node node : nodes[cur.num].neighbor) {
                    stack.push(new NodeRequest(node.num, nextColor));
                }
            }

            if (isValid(nodes))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    static boolean isValid(final Node[] nodes) {
        for (int i = 1; i < nodes.length; i++) {
            for (Node n : nodes[i].neighbor) {
                if (n.color == nodes[i].color)
                    return false;
            }
        }
        return true;
    }

    static class NodeRequest {
        int num;
        int colorRequest;
        NodeRequest(int num, int colorRequest) {
            this.num = num;
            this.colorRequest = colorRequest;
        }
    }

    static class Node {
        int num;
        int color = 0;
        ArrayList<Node> neighbor = new ArrayList<>();

        Node(int num) {
            this.num = num;
        }
    }
}
