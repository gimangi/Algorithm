package BOJ_2568;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Cord[] cords = new Cord[N];
        int[] saveIdx = new int[N];
        ArrayList<Cord> memo = new ArrayList<>();

        // read input
        String[] line;
        for (int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);

            cords[i] = new Cord(start, end);
        }
        memo.add(new Cord(0, 0));

        Arrays.sort(cords, (c1, c2) -> c1.start-c2.start);

        for (int i=0; i<N; i++) {
            if (cords[i].compareTo(memo.get(memo.size()-1)) > 0) {
                saveIdx[i] = memo.size();
                memo.add(cords[i]);
            } else {
                int idx = binSearch(memo, 0, memo.size()-1, cords[i]);
                memo.set(idx, cords[i]);
                saveIdx[i] = idx;
            }
        }

        // trace
        int cur = memo.size() - 1;
        Stack<Integer> trace = new Stack<>();

        for (int i=N-1; i>=0; i--) {
            if (cur == saveIdx[i]) {
                cur -= 1;
            } else {
                trace.push(cords[i].start);
            }
        }

        // print
        System.out.println(trace.size());
        while (!trace.empty()) {
            System.out.println(trace.pop());
        }
    }

    static int binSearch(ArrayList<Cord> list, int left, int right, Cord find) {
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (find.compareTo(list.get(mid)) > 0)
                left = mid + 1;
            else
                right = mid;
        }
        return right;
    }

    static class Cord implements Comparable<Cord> {

        int start;
        int end;
        Cord(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Cord o) {
            if (this.start > o.start && this.end > o.end)
                return 1;
            return -1;
        }
    }

}
