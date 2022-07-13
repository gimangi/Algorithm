import java.util.Scanner;

public class BOJ_20164 {

    static Scanner sc = new Scanner(System.in);

    static int min = Integer.MAX_VALUE;
    static int max = 0;

    public static void main(String args[]) {
        String num = sc.nextLine();
        cut(num, 0);
        System.out.println(min+" "+max);
    }

    static void cut(String str, int cnt) {
        cnt += getOddCount(str);

        if (str.length() == 1) {
            min = Math.min(min, cnt);
            max = Math.max(max, cnt);
        } else if (str.length() == 2) {
            String next = Integer.toString((str.charAt(0)-'0')+(str.charAt(1)-'0'));
            cut(next, cnt);
        } else {
            for (int i=1; i<str.length()-1; i++) {
                for (int j=i+1; j<str.length(); j++) {
                    String next = Integer.toString(Integer.parseInt(str.substring(0, i)) +
                    Integer.parseInt(str.substring(i, j)) +
                    Integer.parseInt(str.substring(j)));

                    cut(next, cnt);
                }
            }
        }

    }

    static int getOddCount(String str) {
        int cnt = 0;
        for (int i=0; i<str.length(); i++)
            if ((str.charAt(i)-'0')%2==1)
                cnt++;
        return cnt;
    }
}
