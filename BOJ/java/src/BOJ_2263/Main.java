package BOJ_2263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] inorder;
    static int[] postorder;

    static int[] indexInorder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        inorder = new int[n + 1];
        postorder = new int[n + 1];
        indexInorder = new int[n + 1];


        // inorder
        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            inorder[i] = num;
            indexInorder[num] = i;
        }

        st = new StringTokenizer(br.readLine());
        // postorder
        for (int i = 1; i <= n; i++)
            postorder[i] = Integer.parseInt(st.nextToken());

        Node root = solution(1, n, 1, n);
        printPreorder(root);

    }

    static Node solution(int ins, int ine, int pos, int poe) {
        if (ins > ine || pos > poe)
            return null;

        int rootNum = postorder[poe];
        int rootIdxInorder = indexInorder[rootNum];
        int leftNodes = rootIdxInorder - ins;

        Node root = new Node(rootNum);
        root.left = solution(ins, rootIdxInorder-1, pos, pos+leftNodes-1);
        root.right = solution(rootIdxInorder+1, ine, pos+leftNodes, poe-1);

        return root;
    }

    static void printPreorder (Node root){
        System.out.print(root.num + " ");
        if (root.left != null)
            printPreorder(root.left);
        if (root.right != null)
            printPreorder(root.right);
    }

    static class Node {
        int num;
        Node left = null;
        Node right = null;

        Node(int num) {
            this.num = num;
        }
    }

}