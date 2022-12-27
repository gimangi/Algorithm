package BOJ_26157;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer>[] adj;
    static int[] sccId, discovered;
    static Stack<Integer> stack;
    static int sccCounter, vertexCounter;
    static List<SCC> sccList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, m;
        String[] inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        m = Integer.parseInt(inputs[1]);

        adj = new ArrayList[n + 1];
        discovered = new int[n + 1];
        sccId = new int[n + 1];
        sccList = new ArrayList<>();

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

        compressDAG();
        SCC first = topologySort();
        if (first == null) {
            System.out.println(0);
            return;
        }

        Collections.sort(first.vertexes);
        StringBuilder sb = new StringBuilder();
        sb.append(first.vertexes.size() + "\n");
        for (int vertex : first.vertexes) {
            sb.append(vertex + " ");
        }
        System.out.println(sb);

    }

    // 그래프를 SCC 단위로 압축
    static void compressDAG() {

        for (SCC scc : sccList) {
            for (int vertex : scc.vertexes) {
                for (int next : adj[vertex]) {
                    if (scc.id != sccId[next]) {
                        SCC linkScc = sccList.get(sccId[next]);
                        scc.link.add(linkScc);
                        linkScc.inDegree++;
                    }
                }
            }
        }
    }

    // SCC 들을 위상절렬 한 후 첫번쨰 SCC 를 반환
    // 큐에 동시에 두 개 이상의 SCC 가 존재하거나 위상정렬이 불가하면 NULL 반환
    static SCC topologySort() {
        Queue<SCC> queue = new LinkedList<>();
        List<SCC> result = new ArrayList<>();

        for (SCC scc : sccList) {
            if (scc.inDegree == 0) {
                queue.offer(scc);
            }
        }

        for (int i = 0; i < sccList.size(); i++) {
            if (queue.isEmpty())
                return null;
            if (queue.size() > 1)
                return null;

            SCC cur = queue.poll();
            for (SCC next : cur.link) {
                if (--next.inDegree == 0) {
                    queue.offer(next);
                }
            }
            result.add(cur);
        }

        return result.get(0);
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
            SCC scc = new SCC(sccCounter);

            while (true) {
                int t = stack.pop();
                sccId[t] = sccCounter;
                scc.vertexes.add(t);
                if (t == here) break;
            }

            sccList.add(scc);
            sccCounter++;
        }
        return ret;
    }


    static class SCC {
        final int id;
        final List<Integer> vertexes = new ArrayList<>();
        final List<SCC> link = new ArrayList<>();

        int inDegree = 0;

        public SCC(int id) {
            this.id = id;
        }
    }
}
