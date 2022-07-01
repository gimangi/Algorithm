import java.util.*;

public class BOJ_Greedy_1399 {
    static Scanner sc = new Scanner(System.in);

    static int[] assign = new int[26];
    static ArrayList<CharInfo> chList = new ArrayList<>();
    static ArrayList<String> strList = new ArrayList<>();

    static int N;
    public static void main(String args[]) {

        N = sc.nextInt();

        for (int i=0; i<26; i++) {
            CharInfo cInfo = new CharInfo((char) ('A'+i));
            chList.add(cInfo);
        }

        for (int i = 0; i < N; i++) {
            String line = sc.next();
            strList.add(line);
            for (int j = line.length() - 1; j >= 0; j--) {
                char c = line.charAt(j);
                int idx = line.length() - j - 1;

                chList.get(c - 'A').cnt += pow(10, idx);
            }
        }

        Collections.sort(chList, (c1, c2) -> c2.cnt-c1.cnt);

        int look = 9;
        for (int i=0; i<chList.size(); i++) {
            CharInfo cInfo = chList.get(i);
            assign[cInfo.ch-'A'] = look--;
        }

        System.out.println(getSum(strList));
    }
    static int getSum(ArrayList<String> words) {
        int sum = 0;
        for (int i=0; i<words.size(); i++) {

            String str = words.get(i);
            for (int pos=str.length()-1; pos>=0; pos--) {
                char c = str.charAt(pos);
                sum += assign[c-'A']*pow(10, str.length()-(pos+1));
            }
        }

        return sum;
    }

    static int pow(int a, int n) {
        int res = 1;
        for (int i=0; i<n; i++)
            res *= a;
        return res;
    }
}

class CharInfo {
    char ch;
    int cnt;

    CharInfo(char ch) {
        this.ch = ch;
        this.cnt = 0;
    }
}