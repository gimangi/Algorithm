package BOJ_2343;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        StringTokenizer st = new StringTokenizer(line);

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        line = sc.nextLine();
        st = new StringTokenizer(line);

        int[] lectureLen = new int[N];
        int start = 0;
        int end = 0;
        int ans = Integer.MAX_VALUE;

        for (int i=0; i<N; i++) {
            lectureLen[i] = Integer.parseInt(st.nextToken());
            end += lectureLen[i];
        }

        while (start <= end) {
            int mid = (start + end) / 2;    // 탐색중인 블루레이 크기

            int curStat = 0;
            int numBlueray = 1;
            for (int i=0; i<N; i++) {
                if (lectureLen[i] > mid) {
                    numBlueray = -1;
                    break;
                }

                curStat += lectureLen[i];

                if (curStat > mid) {
                    curStat = lectureLen[i];
                    numBlueray++;
                }
            }

            if (numBlueray == -1) {     // 불가능한 블루레이 크기 -> 백트래킹 느낌으로 이전 기준 오르쪽으로 이동
                end = mid * 2 + 1;
                start = mid + 1;
            } else {
                if (numBlueray <= M) {
                    ans = Math.min(ans, mid);
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }

        System.out.println(ans);

    }
}
