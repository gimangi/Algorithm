package BOJ_2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T, N, M;
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        int[] arrayA = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            arrayA[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        int[] arrayB = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<M; i++)
            arrayB[i] = Integer.parseInt(st.nextToken());

        ArrayList<Integer> subSumA = getSubSumList(arrayA);
        ArrayList<Integer> subSumB = getSubSumList(arrayB);
        long cnt = 0;

        Collections.sort(subSumB);

        for (int sum : subSumA) {
            int tar = T - sum;
            cnt += upperBound(subSumB, tar) - lowerBound(subSumB, tar);
        }
        System.out.println(cnt);
    }

    static int upperBound(final ArrayList<Integer> list, int find) {
        int left = 0;
        int right = list.size();
        int mid;

        while (left < right) {
            mid = (left + right) / 2;
            if (list.get(mid) <= find)
                left = mid + 1;
            else
                right = mid;
        }
        return right;
    }

    static int lowerBound(final ArrayList<Integer> list, int find) {
        int left = 0;
        int right = list.size();
        int mid;

        while (left < right) {
            mid = (left + right) / 2;
            if (list.get(mid) < find)
                left = mid + 1;
            else
                right = mid;
        }
        return right;
    }

    static ArrayList<Integer> getSubSumList(final int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i=0; i<arr.length; i++) {
            int sum = 0;
            for (int j=i; j<arr.length; j++) {
                sum += arr[j];
                list.add(sum);
            }
        }
        return list;
    }
}
