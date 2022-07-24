package BOJ_11049;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Matrix> matrices = new ArrayList<>();

        for (int i=0; i<N; i++) {
            String[] line = br.readLine().split(" ");
            matrices.add(new Matrix(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
        }

        int[][] dp = new int[N][N];

        for (int j=0; j<N; j++) {
            for (int i=N-1; i>=0; i--) {

                for (int k=i; k<=j-1; k++) {
                    if (i==j)
                        dp[i][j] = 0;
                    else if (i<j) {
                        if (dp[i][j] != 0)
                            dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + matrices.get(i).r * matrices.get(k).c * matrices.get(j).c);
                        else
                            dp[i][j] = dp[i][k] + dp[k + 1][j] + matrices.get(i).r * matrices.get(k).c * matrices.get(j).c;
                    }
                    else
                        dp[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        System.out.println(dp[0][N-1]);

    }
}

class Matrix {
    int r;
    int c;
    Matrix(int r, int c) {
        this.r = r;
        this.c = c;
    }
}