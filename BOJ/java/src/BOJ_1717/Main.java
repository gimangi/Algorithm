package BOJ_1717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M;
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        int[] parent = new int[N+1];
        // init
        for (int i=0; i<=N; i++)
            parent[i] = i;

        for (int i=0; i<M; i++) {
            line = br.readLine().split(" ");
            int op = Integer.parseInt(line[0]);
            int set1 = Integer.parseInt(line[1]);
            int set2 = Integer.parseInt(line[2]);

            if (op == 0) {
                union(parent, set1, set2);
            } else if (op == 1) {
                int find1 = find(parent, set1);
                int find2 = find(parent, set2);
                if (find1 == find2)
                    System.out.println("YES");
                else
                    System.out.println("NO");
            }
        }
    }

    static int find(final int[] parent, int i) {
        if (parent[i] == i)
            return i;
        return parent[i] = find(parent, parent[i]);
    }

    static void union(final int[] parent, int a, int b) {
        int parentA = find(parent, a);
        int parentB = find(parent, b);

        if (parentA < parentB)
            parent[parentB] = parentA;
        else
            parent[parentA] = parentB;
    }

}
