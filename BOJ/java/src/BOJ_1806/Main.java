package BOJ_1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, S;
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        S = Integer.parseInt(line[1]);
        int[] seq = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            seq[i] = Integer.parseInt(st.nextToken());

        int start = 0;
        int end = 0;
        int sum = seq[start];
        int ans = Integer.MAX_VALUE;

        for (; start < N; start++) {
            while (sum < S && end < N - 1)
                sum += seq[++end];

            if (sum >= S)
                ans = Math.min(ans, end - start + 1);
            sum -= seq[start];
        }

        if (ans == Integer.MAX_VALUE)
            ans = 0;
        System.out.println(ans);
    }
}
