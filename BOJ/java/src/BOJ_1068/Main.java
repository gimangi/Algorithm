package BOJ_1068;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new Node[N];
        // init
        for (int i = 0; i < N; i++) {
            tree[i] = new Node();
        }

        String[] inputs = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int parentNum = Integer.parseInt(inputs[i]);
            Node parent = null;
            if (parentNum != -1) {
                parent = tree[parentNum];
                tree[parentNum].children.add(tree[i]);
            }
            tree[i].parent = parent;
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
        Node parent;
        final List<Node> children = new ArrayList<>();
        boolean isDeleted = false;

        public boolean isLeaf() {
            if (children.isEmpty())
                return true;

            for (Node child : children) {
                if (!child.isDeleted)
                    return false;
            }
            return true;
        }

    }

}
