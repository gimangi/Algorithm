package BOJ_14003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] seq = new int[N];
        int[] dp = new int[N+1];
        ArrayList<Integer>[] save = new ArrayList[N+1];

        // init
        for (int i=0; i<=N; i++) {
            dp[i] = Integer.MIN_VALUE;
            save[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int len = 0;
        for (int i=0; i<N; i++) {
            if (seq[i] > dp[len]) {
                len += 1;
                dp[len] = seq[i];
                save[len].add(i);
            } else {
                int idx = binSearch(dp, 0, len, seq[i]);
                dp[idx] = seq[i];
                save[idx].add(i);
            }
        }

        System.out.println(len);
        int[] ans = new int[N+1];
        int prev = Integer.MAX_VALUE;

        for (int i=N; i>0; i--) {
            Collections.sort(save[i]);
            for (int idx : save[i]) {
                if (idx < prev) {
                    if ((prev < Integer.MAX_VALUE && seq[idx] < seq[prev]) || prev == Integer.MAX_VALUE) {

                        ans[i] = seq[idx];
                        prev = idx;
                        break;
                    }
                }
            }
        }

        for (int i=1; i<=len; i++) {
            System.out.print(ans[i] + " ");
        }

    }

    static int binSearch(int[] arr, int left, int right, int find) {
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] < find)
                left = mid + 1;
            else
                right = mid;
        }
        return right;
    }
}
