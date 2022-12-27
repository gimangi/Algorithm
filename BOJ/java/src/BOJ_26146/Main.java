package BOJ_26146;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    static List<Integer>[] adj;
    static int[] sccId, discovered;
    static Stack<Integer> stack;
    static int sccCounter, vertexCounter;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, m;
        String[] inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        m = Integer.parseInt(inputs[1]);

        adj = new ArrayList[n + 1];
        discovered = new int[n + 1];
        sccId = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
            discovered[i] = -1;
            sccId[i] = -1;
        }

        for (int i = 0; i < m; i++) {
            inputs = br.readLine().split(" ");
            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);
            adj[from].add(to);
        }

        stack = new Stack<>();

        for (int i = 1; i < n + 1; i++) {
            if (discovered[i] == -1) {
                scc(i);
            }
        }

        if (sccCounter == 1)
            System.out.println("Yes");
        else
            System.out.println("No");

    }

    static int scc(int here) {
        int ret = discovered[here] = vertexCounter++;
        stack.push(here);

        for (int there : adj[here]) {
            if (discovered[there] == -1) {
                ret = Math.min(ret, scc(there));
            }
            else if (sccId[there] == -1) {
                ret = Math.min(ret, discovered[there]);
            }
        }

        if (ret == discovered[here]) {
            while (true) {
                int t = stack.pop();
                sccId[t] = sccCounter;
                if (t == here) break;
            }

            sccCounter++;
        }
        return ret;
    }
}
