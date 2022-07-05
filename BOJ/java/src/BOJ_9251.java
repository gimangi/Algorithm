import java.util.Scanner;

public class BOJ_9251 {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        int dp[][] = new int[str1.length()+1][str2.length()+1];

        for (int i=1; i<dp.length; i++) {
            char c1 = str1.charAt(i-1);

            for (int j=1; j<dp[0].length; j++) {
                char c2 = str2.charAt(j-1);

                if (c1 == c2)
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }

        System.out.println(dp[dp.length-1][dp[0].length-1]);

    }
}
