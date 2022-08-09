package BOJ_12015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] seq = new int[N];
        int[] dp = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int len = 0;
        for (int i=0; i<N; i++) {
            if (seq[i] > dp[len]) {
                len += 1;
                dp[len] = seq[i];
            } else {
                dp[binSearch(dp, 0, len, seq[i])] = seq[i];
            }
        }

        System.out.println(len);
    }

    public static int binSearch(int[] arr, int left, int right, int find) {
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
