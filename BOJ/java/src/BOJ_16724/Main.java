package BOJ_16724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M;
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        int[] parent = new int[N * M];
        for (int i = 0; i < N * M; i++)
            parent[i] = i;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                int curIdx = i * M + j;
                int parIdx = curIdx;
                switch (str.charAt(j)) {
                    case 'D':
                        parIdx += M;
                        break;
                    case 'U':
                        parIdx -= M;
                        break;
                    case 'R':
                        parIdx += 1;
                        break;
                    case 'L':
                        parIdx -= 1;
                }

                parent[curIdx] = find(parent, parIdx);
            }
        }

        for (int i = 0; i < M * N; i++)
            parent[i] = find(parent, i);

        HashMap<Integer, Boolean> parentMap = new HashMap();
        int ans = 0;
        for (int i = 0; i < M * N; i++) {
            if (!parentMap.containsKey(parent[i])) {
                parentMap.put(parent[i], true);
                ans++;
            }
        }
        System.out.println(ans);
    }

    static int find(final int[] parent, int i) {
        if (parent[i] == i)
            return i;
        return parent[i] = find(parent, parent[i]);
    }
}
