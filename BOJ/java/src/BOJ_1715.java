import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1715 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException {
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int sum = 0;

        for (int i=0; i<N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        while (pq.size() >= 2) {
            int a = pq.poll();
            int b = pq.poll();
            pq.add(a+b);
            sum += (a+b);
        }

        System.out.println(sum);
    }
}
