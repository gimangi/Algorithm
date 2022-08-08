package BOJ_5639;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(br.readLine()));

        while (true) {
            String line = br.readLine();
            if (line == null || line.isEmpty())
                break;
            root.insert(Integer.parseInt(line));
        }

        root.printPostOrder();
    }
}

class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
    }

    public void insert(int value) {
        if (this.value > value) {
            if (left == null)
                left = new Node(value);
            else
                left.insert(value);
        }
        else {
            if (right == null)
                right = new Node(value);
            else
                right.insert(value);
        }
    }

    public void printPostOrder() {
        if (left != null)
            left.printPostOrder();
        if (right != null)
            right.printPostOrder();
        System.out.println(value);
    }
}