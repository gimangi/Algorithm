package BOJ_1208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, S;
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        S = Integer.parseInt(line[1]);
        int[] first = new int[N / 2];
        int[] second = new int[N - first.length];
        line = br.readLine().split(" ");

        for (int i=0; i<N; i++) {
            if (i < first.length)
                first[i] = Integer.parseInt(line[i]);
            else
                second[i - first.length] = Integer.parseInt(line[i]);
        }

        ArrayList<Integer> firstComb = getComb(first);
        ArrayList<Integer> secondComb = getComb(second);
        HashMap<Integer, Integer> firstMap = new HashMap();

        long ans = 0;
        for (int num : firstComb) {
            firstMap.merge(num, 1, (a, b) -> (int) a + b);
            if (num == S)
                ans++;
        }

        for (int num : secondComb) {
            if (num == S)
                ans++;
            if (firstMap.containsKey(S - num))
                ans += firstMap.get(S - num);
        }
        System.out.println(ans);
    }

    static ArrayList<Integer> getComb(final int[] arr) {
        ArrayList<Integer> ret = new ArrayList<>();
        Stack<Num> st = new Stack<>();

        if (arr.length > 0)
            st.push(new Num(-1, 0, 0));

        while (!st.isEmpty()) {
            Num cur = st.pop();
            if (cur.idx == arr.length - 1) {
                if (cur.cnt > 0)
                    ret.add(cur.value);
                continue;
            }

            st.push(new Num(cur.idx + 1, cur.cnt + 1, cur.value + arr[cur.idx + 1]));
            st.push(new Num(cur.idx + 1, cur.cnt, cur.value));
        }

        return ret;
    }

    static class Num {
        int idx;
        int value;
        int cnt;
        Num(int idx, int cnt, int value) {
            this.idx = idx;
            this.cnt = cnt;
            this.value = value;
        }
    }
}
