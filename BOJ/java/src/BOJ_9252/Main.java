package BOJ_9252;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        int len1 = str1.length();
        int len2 = str2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }

        System.out.println(dp[len1][len2]);
        String ans = "";

        int i = len1;
        int j = len2;
        Stack<Character> st = new Stack<>();
        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i - 1][j])
                i -= 1;
            else if (dp[i][j] == dp[i][j - 1])
                j -= 1;
            else if (dp[i][j] == dp[i - 1][j - 1]) {
                i -= 1;
                j -= 1;
            } else {
                st.push(str2.charAt(j - 1));
                i -= 1;
                j -= 1;
            }
        }
        while (!st.empty()) {
            ans += st.pop();
        }

        System.out.println(ans);

    }

}
