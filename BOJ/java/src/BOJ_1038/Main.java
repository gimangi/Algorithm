package BOJ_1038;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(solution(n));
    }

    public static long solution(int n) {

        if (n == 0)
            return 0;

        int cnt = 0;
        long num = 0;

        while (num <= 9876543210l) {
            int increaseIdx = getIncreaseIdx(num);

            if (increaseIdx == -1) {
                if (cnt == n)
                    return num;

                num++;
                cnt++;
            } else {
                long idx = (long) Math.pow(10, increaseIdx+1);
                if (num % idx != 0) {
                    num /= idx;
                    num *= idx;
                    num += idx;
                } else {
                    num += 1;
                }
            }

        }

        return -1;
    }

    /*
        감소하지 않는 위치를 반환 (10^n의 자리)
        감소하는 수라면 -1을 반환
     */
    public static int getIncreaseIdx(long num) {
        String numStr = Long.toString(num);
        for (int i=1; i<numStr.length(); i++) {
            if (numStr.charAt(i-1)-'0' <= numStr.charAt(i)-'0')
                return numStr.length()-i-1;
        }
        return -1;
    }
}
