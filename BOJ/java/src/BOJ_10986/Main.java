package BOJ_10986;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M;
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cnt = new int[M];

        long sum = 0;
        long ans = 0;

        for (int i=0; i<N; i++) {
            sum += Integer.parseInt(st.nextToken());
            int idx = (int) (sum % M);
            if (idx == 0)
                ans += ++cnt[idx];
            else
                ans += cnt[idx]++;
        }
        System.out.println(ans);
    }
}
