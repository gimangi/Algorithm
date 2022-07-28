package BOJ_5052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            ArrayList<String> list = new ArrayList<>();
            for (int i=0; i<n; i++) {
                list.add(br.readLine());
            }

            Collections.sort(list);

            if (isAccept(list))
                System.out.println("YES");
            else
                System.out.println("NO");

        }

    }

    public static boolean isAccept(final ArrayList<String> list) {
        for (int i=0; i<list.size()-1; i++) {
            String prev = list.get(i);
            String next = list.get(i+1);

            if (next.startsWith(prev))
                    return false;

        }
        return true;
    }
}
