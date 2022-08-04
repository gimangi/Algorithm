package BOJ_2417;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long ans = Long.MAX_VALUE;

        long start = 0, end = n;

        while (start <= end) {
            long mid = (start + end) / 2;

            if ((long) Math.pow(mid, 2) >= n) {
                ans = Math.min(mid, ans);

                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }
        System.out.println(ans);

     }
}
