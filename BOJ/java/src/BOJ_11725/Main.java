package BOJ_11725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        // init
        for (int i=0; i<=N+1; i++)
            list.add(new ArrayList());

        int[] parents = new int[N+1];

        for (int i=0; i<N-1; i++) {
            String[] line = br.readLine().split(" ");
            int left = Integer.parseInt(line[0]);
            int right = Integer.parseInt(line[1]);
            list.get(left).add(right);
            list.get(right).add(left);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        while (!queue.isEmpty()) {
            int parent = queue.poll();

            for (int item : list.get(parent)) {
                if (parents[item] == 0) {
                    parents[item] = parent;
                    queue.offer(item);
                }
            }
        }

        for (int i=2; i<=N; i++) {
            System.out.println(parents[i]);
        }

    }
}
