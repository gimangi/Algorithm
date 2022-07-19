package BOJ_12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N, K;
        String line[] = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        K = Integer.parseInt(line[1]);
        ArrayList<Item> items = new ArrayList<>();

        for (int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            items.add(new Item(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
        }

        int dp[][] = new int[N+1][K+1];
        dp[0][0] = 0;

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=K; j++) {
                Item item = items.get(i-1);
                int put = -1;
                if (j-item.weight >= 0)
                    put = dp[i-1][j-item.weight]+item.price;
                dp[i][j] = Math.max(dp[i-1][j], put);
            }
        }

        System.out.println(dp[N][K]);

    }
}

class Item {
    int weight;
    int price;

    Item(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }
}