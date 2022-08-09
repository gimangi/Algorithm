package BOJ_1967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

    static int n;
    static ArrayList<Edge>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];

        // init
        for (int i=0; i<=n; i++)
            graph[i] = new ArrayList<>();

        String[] line;
        for (int i=0; i<n-1; i++) {
            line = br.readLine().split(" ");

            int parent = Integer.parseInt(line[0]);
            int child = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);

            graph[parent].add(new Edge(child, weight));
            graph[child].add(new Edge(parent, weight));
        }

        // 가장 깊은 곳에 있는 노드 찾기
        int leaf = dfs(1)[1];
        int ans = dfs(leaf)[0];
        System.out.println(ans);
    }

    // len / node
    static int[] dfs(int start) {
        Stack<Edge> st = new Stack<>();
        boolean[] visited = new boolean[n+1];
        st.push(new Edge(start, 0));
        int maxLen = 0;
        int leaf = 0;

        while (!st.isEmpty()) {
            Edge edge = st.pop();

            if (!visited[edge.vertex]) {
                visited[edge.vertex] = true;

                for (Edge next : graph[edge.vertex]) {
                    st.push(new Edge(next.vertex, next.weight, edge.sum + next.weight));
                }

                if (maxLen < edge.sum) {
                    maxLen = edge.sum;
                    leaf = edge.vertex;
                }
            }
        }

        int[] ans = new int[2];
        ans[0] = maxLen;
        ans[1] = leaf;
        return ans;
    }

    static class Edge {
        int vertex;
        int weight;

        int sum = 0;

        Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        Edge(int vertex, int weight, int sum) {
            this(vertex, weight);
            this.sum = sum;
        }
    }
}
