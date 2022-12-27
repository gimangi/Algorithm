package BOJ_2150;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer>[] adj;
    static int[] sccId, discovered;
    static Stack<Integer> stack;
    static int sccCounter, vertexCounter;
    static List<Queue<Integer>> scc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int v, e;

        v = Integer.parseInt(inputs[0]);
        e = Integer.parseInt(inputs[1]);

        adj = new ArrayList[v + 1];
        discovered = new int[v + 1];
        sccId = new int[v + 1];
        for (int i = 1; i < v + 1; i++) {
            adj[i] = new ArrayList<>();
            discovered[i] = -1;
            sccId[i] = -1;
        }

        for (int i = 0; i < e; i++) {
            inputs = br.readLine().split(" ");
            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);
            adj[from].add(to);
        }

        scc = new ArrayList<>();
        stack = new Stack<>();

        for (int i = 1; i < v + 1; i++) {
            if (discovered[i] == -1) {
                scc(i);
            }
        }

        System.out.println(sccCounter);
        Collections.sort(scc, ((o1, o2) -> o1.peek() - o2.peek()));

        StringBuilder sb = new StringBuilder();
        for (Queue<Integer> q : scc) {
            while (!q.isEmpty()) {
                sb.append(q.poll() + " ");
            }
            sb.append(-1 + "\n");
        }
        System.out.println(sb);
    }

    static int scc(int here) {
        int parent = discovered[here] = vertexCounter++;
        stack.push(here);

        for (int i = 0; i < adj[here].size(); i++) {
            int there = adj[here].get(i);
            if (discovered[there] == -1) {
                parent = Math.min(parent, scc(there));
            }
            else if (sccId[there] == -1) {
                parent = Math.min(parent, discovered[there]);
            }
        }

        if (parent == discovered[here]) {
            Queue<Integer> queue = new PriorityQueue<>();
            while (true) {
                int t = stack.pop();
                queue.add(t);
                sccId[t] = sccCounter;
                if (t == here) break;
            }

            scc.add(queue);
            sccCounter++;
        }
        return parent;
    }
}
