package BOJ_1655;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> smaller = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> larger = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        int mid = Integer.parseInt(br.readLine());
        sb.append(mid+"\n");

        for (int i=1; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num < mid)
                smaller.offer(num);
            else
                larger.offer(num);

            int sSize = smaller.size();
            int lSize = larger.size();
            if (sSize + 1 < lSize) {
                smaller.offer(mid);
                mid = larger.poll();
            }
            else if (sSize > lSize) {
                larger.offer(mid);
                mid = smaller.poll();
            }
            sb.append(mid+"\n");
        }
        System.out.println(sb);
    }
}
