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

//        ArrayList<Integer> match = KMP(T, P);
        ArrayList<Integer> match = rabinKarp(T, P);

        System.out.println(match.size());
        for (int idx : match)
            System.out.print(idx+" ");
    }


    static ArrayList<Integer> rabinKarp(String parent, String pattern) {
        ArrayList<Integer> ans = new ArrayList<>();

        int parentLen = parent.length();
        int patternLen = pattern.length();

        int patternHash = 0;
        int curHash = 0;
        int power = 1;

        for (int i=0; i<=parentLen-patternLen; i++) {
            if (i == 0) {
                for (int j=patternLen-1; j>=0; j--) {
                    patternHash += pattern.charAt(j) * power;
                    curHash += parent.charAt(j) * power;
                    if (j > 0) power *= 3;
                }
            } else {
                curHash = 3 * (curHash - parent.charAt(i-1) * power) + parent.charAt(i + patternLen - 1);
            }

            if (patternHash == curHash) {
                boolean match = true;
                for (int j=0; j<patternLen; j++) {
                    if (parent.charAt(j+i) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match)
                    ans.add(i+1);
            }
        }
        return ans;
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
