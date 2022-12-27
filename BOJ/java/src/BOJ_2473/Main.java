package BOJ_2473;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N;
        List<Integer> waters = new ArrayList<>();

        N = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        for (String input : inputs) {
            waters.add(Integer.parseInt(input));
        }

        waters.sort((w1, w2) -> w1 - w2);

        int[] result = new int[3];
        long sum = Long.MAX_VALUE;

        for (int i = 0; i < waters.size() - 1; i++) {
            for (int j = i + 1; j < waters.size(); j++) {
                int look = (waters.get(i) + waters.get(j)) * -1;
                int pick = binSearch(waters, look);

                long matchPick = matched(waters, sum, i, j, pick);
                if (matchPick != -1) {
                    sum = matchPick;
                    saveResult(result, i, j, pick);
                }

                if (pick == 0) continue;

                long matchPickSecond = matched(waters, sum, i, j, pick - 1);
                if (matchPickSecond != -1) {
                    sum = matchPickSecond;
                    saveResult(result, i, j, pick - 1);
                }
            }
        }

        for (int idx : result) {
            System.out.print(waters.get(idx) + " ");
        }
    }

    static void saveResult(int[] result, int i, int j, int pick) {
        result[0] = i;
        result[1] = j;
        result[2] = pick;
        Arrays.sort(result);
    }

    // Returns abs if the condition is satisfied, otherwise returns -1
    static long matched(List<Integer> list, long sum, int i, int j, int picked) {
        long abs = Math.abs((long) list.get(i) + list.get(j) + list.get(picked));
        if (i != j && picked != i && picked != j && abs < sum)
            return abs;
        return -1;
    }

    // Find the nearest number that is not less than 'num'
    static int binSearch(List<Integer> list, int num) {
        int low = 0, high = list.size() - 1, mid = 0;

        while (low < high) {
            mid = (low + high) / 2;
            int look = list.get(mid);

            if (look == num) {
                return mid;
            }
            else if (look > num) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }

            mid = (low + high) / 2;
        }

        return mid;
    }
}
