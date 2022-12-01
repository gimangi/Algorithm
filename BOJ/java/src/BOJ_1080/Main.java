package BOJ_1080;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] matA;
    static int[][] matB;
    static int N, M;
    static int answer = 0;

    public static void main(String[] args) throws IOException, IllegalArgumentException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matA = new int[N][M];
        matB = new int[N][M];

        initMat(matA, N, M);
        initMat(matB, N, M);

        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 2; j++) {
                if (matA[i][j] != matB[i][j]) {
                    flip(matA, i, j);
                    answer++;
                }
            }
        }

        if (!isEqual(matA, matB)) {
            answer = -1;
        }

        System.out.println(answer);
    }

    static void initMat(int[][] mat, int n, int m) throws IOException {
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                mat[i][j] = line.charAt(j) + '0';
            }
        }
    }

    static void flip(int[][] mat, int r, int c) {
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                mat[i][j] ^= 1;
            }
        }
    }

    static boolean isEqual(int[][] mat1, int[][] mat2) throws IllegalArgumentException {
        if (mat1.length != mat2.length)
            throw new IllegalArgumentException();

        for (int i = 0; i < mat1.length; i++){
            if (mat1[i].length != mat2[i].length)
                throw new IllegalArgumentException();

            for (int j = 0; j < mat1[i].length; j++) {
                if (mat1[i][j] != mat2[i][j])
                    return false;
            }
        }

        return true;
    }


}
