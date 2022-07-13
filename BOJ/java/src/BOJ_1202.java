import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_1202 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException {
        int N, K;
        ArrayList<Jewel> listJewel = new ArrayList<>();
        ArrayList<Integer> listBag = new ArrayList<>();
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        K = Integer.parseInt(str[1]);
        long sum = 0L;

        for (int i=0; i<N; i++) {
            String[] inputs = br.readLine().split(" ");
            Jewel jewel = new Jewel(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
            listJewel.add(jewel);
        }

        for (int j=0; j<K; j++) {
            int input = Integer.parseInt(br.readLine());
            listBag.add(input);
        }

        Collections.sort(listJewel, new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                if (o1.weight == o2.weight)
                    return o2.price - o1.price;
                return o1.weight - o2.weight;
            }
        });
        Collections.sort(listBag);

        PriorityQueue<Jewel> pqJewel = new PriorityQueue<>();
        for (int i=0, j=0; i<K; i++) {
            while (j<N && listJewel.get(j).weight<=listBag.get(i)) {
                pqJewel.offer(listJewel.get(j++));
            }

            if (!pqJewel.isEmpty()) {
                sum += pqJewel.poll().price;
            }
        }

        System.out.println(sum);

    }
}

class Jewel implements Comparable<Jewel>{

    int weight;
    int price;

    Jewel(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    @Override
    public int compareTo(Jewel o) {
        if (this.price<o.price)
            return 1;
        else if (this.price==o.price && this.weight>o.weight)
            return 1;
        else if (this.price==o.price && this.weight==o.weight)
            return 0;
        else
            return -1;
    }
}
