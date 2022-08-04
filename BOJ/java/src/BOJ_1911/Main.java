package BOJ_1911;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int N, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Pool> poolList = new ArrayList<>();

        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        L = Integer.parseInt(line[1]);

        for (int i = 0; i < N; i++) {
            line = br.readLine().split(" ");
            Pool pool = new Pool(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
            poolList.add(pool);
        }

        Collections.sort(poolList, (p1, p2) -> p1.start - p2.start);

        int ans = 0;
        int range = 0;
        for (int i=0; i<N; i++) {
            Pool pool = poolList.get(i);

            if (pool.start > range)
                range = pool.start;
            while (pool.end > range) {
                range += L;
                ans++;
            }
        }

        System.out.println(ans);
    }
}

class Pool {
    public int start;
    public int end;

    Pool(int start, int end) {
        this.start = start;
        this.end = end;
    }

}
