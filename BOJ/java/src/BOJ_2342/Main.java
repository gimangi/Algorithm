package BOJ_2342;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer> steps;
    static int[][][] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        steps = new ArrayList<>();
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 0)
                break;
            steps.add(num);
        }
        N = steps.size();
        dp = new int[N + 1][5][5];
        System.out.println(solution(0, 0,0));
    }

    static int solution(int step, int left, int right) {
        if (step == N)
            return 0;
        if (dp[step][left][right] != 0)
            return dp[step][left][right];
        int lCost = getCost(left, steps.get(step)) + solution(step + 1, steps.get(step), right);
        int rCost = getCost(right, steps.get(step)) + solution(step + 1, left, steps.get(step));
        return dp[step][left][right] = Math.min(lCost, rCost);
    }

    static int getCost(int from, int to) {
        if (from == to) return 1;
        if (from == 0) return 2;
        if (Math.abs(from - to) == 2) return 4;
        return 3;
    }

}
