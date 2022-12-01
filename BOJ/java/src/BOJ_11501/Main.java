package BOJ_11501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            long result = 0;

            int N = Integer.parseInt(br.readLine());
            ArrayList<Integer> prices = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                prices.add(Integer.parseInt(st.nextToken()));
            }

            long max = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (prices.get(i) > max)
                    max = prices.get(i);
                else
                    result += (max - prices.get(i));
            }

            System.out.println(result);
        }
    }
}
