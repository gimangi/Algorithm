package BOJ_1253;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine());

        String line = sc.nextLine();
        StringTokenizer st = new StringTokenizer(line);

        ArrayList<Integer> numList = new ArrayList<>();
        HashMap<Integer, ArrayList<Number>> numMap = new HashMap<>();

        for (int i=0; i<N; i++) {
            int next = Integer.parseInt(st.nextToken());
            numList.add(next);

            ArrayList<Number> list = numMap.get(next);
            if (list == null) {
                list = new ArrayList();
                numMap.put(next, list);
            }
            list.add(new Number(next, i));

        }

        int ans = 0;

        for (int i=0; i<N; i++) {
            int cur = numList.get(i);

            breakP:
            for (int j=0; j<N; j++) {
                if (i == j)
                    continue;

                int pick = numList.get(j);

                ArrayList<Number> peers = numMap.get(cur-pick);
                if (peers == null)
                    continue;

                for (Number peer : peers) {
                    if (peer.index != j && peer.index != i) {
                        ans++;
                        break breakP;
                    }
                }
            }
        }

        System.out.println(ans);

    }
}

class Number {
    int num;
    int index;

    Number(int num, int index) {
        this.num = num;
        this.index = index;
    }
}