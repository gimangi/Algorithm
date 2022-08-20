package BOJ_10775;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G, P;
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        int[] gates = new int[G + 1];
        for (int i=0; i<=G; i++)
            gates[i] = i;

        int cnt = 0;
        for (; cnt < P; cnt++) {
            int gi = Integer.parseInt(br.readLine());
            int idx = find(gates, gi);
            if (idx > 0) {
                gates[idx] = find(gates, idx - 1);
            }
            else
                break;
        }

        System.out.println(cnt);
    }

    static int find(final int[] gates, int i) {
        if (i <= 0)
            return -1;
        if (gates[i] == i)
            return i;
        return gates[i] = find(gates, gates[i]);
    }
}
