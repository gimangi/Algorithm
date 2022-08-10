package BOJ_1786;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String T = br.readLine();
        String P = br.readLine();

        ArrayList<Integer> match = KMP(T, P);

        System.out.println(match.size());
        for (int idx : match)
            System.out.print(idx+" ");
    }

    static ArrayList<Integer> KMP(String parent, String pattern) {
        ArrayList<Integer> ans = new ArrayList<>();

        int parentLen = parent.length();
        int patternLen = pattern.length();

        int[] table = getTable(pattern);

        int j = 0;
        for (int i = 0; i < parentLen; i++) {
            while (j > 0 && parent.charAt(i) != pattern.charAt(j))
                j = table[j - 1];
            if (parent.charAt(i) == pattern.charAt(j)) {
                if (j == patternLen - 1) {
                    ans.add(i - patternLen + 2);
                    j = table[j];
                } else {
                    j += 1;
                }
            }
        }

        return ans;
    }

    static int[] getTable(String str) {
        int len = str.length();
        int[] ret = new int[len];
        int j = 0;

        for (int i=1; i<len; i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j))
                j = ret[j - 1];
            if (str.charAt(i) == str.charAt(j))
                ret[i] = ++j;
        }

        return ret;
    }
}
