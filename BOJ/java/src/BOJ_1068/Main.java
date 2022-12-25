package BOJ_1068;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new Node[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            Node parent = null;
            int parentNum = Integer.parseInt(st.nextToken());
            if (parentNum != -1) {
                parent = tree[parentNum];
                tree[parentNum].children.add(tree[i]);
            }
            tree[i] = new Node(parent);
        }

        int delete = Integer.parseInt(br.readLine());
        deleteNode(tree[delete]);

        System.out.println(numOfLeafs());
    }

    public static void deleteNode(Node node) {
        for (Node child : node.children) {
            deleteNode(child);
        }
        node.isDeleted = true;
    }

    public static int numOfLeafs() {
        int count = 0;

        for (Node node : tree) {
            if (!node.isDeleted && node.isLeaf())
                count++;
        }

        return count;
    }

    static class Node {
        final Node parent;
        final List<Node> children = new ArrayList<>();
        boolean isDeleted = false;

        public Node(Node parent) {
            this.parent = parent;
        }

        public boolean isLeaf() {
            return children.isEmpty();
        }

    }

}
