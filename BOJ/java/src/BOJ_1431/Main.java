package BOJ_1431;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<String> list = new ArrayList<>();

        for (int i=0; i<N; i++) {
            list.add(br.readLine());
        }

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length())
                    return 1;
                else if (o1.length() < o2.length())
                    return -1;
                else {
                    int sum1 = getSum(o1);
                    int sum2 = getSum(o2);

                    if (sum1 > sum2)
                        return 1;
                    else if (sum1 < sum2)
                        return -1;
                    else
                        return o1.compareTo(o2);
                }
            }
        });

        list.forEach((str) -> {
            System.out.println(str);
        });
    }

    public static int getSum(String str) {
        int sum = 0;

        for (int i=0; i<str.length(); i++) {
            if ('0' <= str.charAt(i) && str.charAt(i) <= '9')
                sum += str.charAt(i) - '0';
        }

        return sum;
    }

}
