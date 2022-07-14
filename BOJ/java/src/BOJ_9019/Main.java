package BOJ_9019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t=0; t<T; t++) {
            String input[] = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);

            boolean visited[] = new boolean[10000];
            Queue<Operation> queue = new LinkedList<>();
            queue.offer(new Operation(start, ""));
            visited[start] = true;

            String ans = "";

            outLoop:
            while (!queue.isEmpty()) {
                int qSize = queue.size();

                for (int i = 0; i < qSize; i++) {
                    Operation op = queue.poll();
                    int next = op.num;

                    if (next == end) {
                        ans = op.opName;
                        break outLoop;
                    }

                    int d = (2 * next) % 10000;
                    int s = next == 0 ? 9999 : next - 1;
                    int l = getLeft(next);
                    int r = getRight(next);

                    if (!visited[d]) {
                        queue.offer(new Operation(d, op.opName +"D"));
                        visited[d] = true;
                    }
                    if (!visited[s]) {
                        queue.offer(new Operation(s, op.opName +"S"));
                        visited[s] = true;
                    }
                    if (!visited[l]) {
                        queue.offer(new Operation(l, op.opName +"L"));
                        visited[l] = true;
                    }
                    if (!visited[r]) {
                        queue.offer(new Operation(r, op.opName +"R"));
                        visited[r] = true;
                    }
                }
            }
            System.out.println(ans);

        }
    }

    static int getLeft(int num) {
        return swapDigit(num, 1, 2, 3, 0);
    }

    static int getRight(int num) {
        return swapDigit(num, 3, 0, 1, 2);
    }

    static int swapDigit(int num, int d1, int d2, int d3, int d4) {
        String nStr = Integer.toString(num);
        nStr = getZeros(4-nStr.length()) + nStr;
        String newStr = nStr.charAt(d1) + "" + nStr.charAt(d2) + "" + nStr.charAt(d3) + "" + nStr.charAt(d4);
        return Integer.parseInt(newStr);
    }

    static String getZeros(int n) {
        String res = "";
        for (int i=0; i<n; i++)
            res += "0";
        return res;
    }

}

class Operation {
    int num;
    String opName;

    Operation(int num, String op) {
        this.num = num;
        this.opName = op;
    }
}