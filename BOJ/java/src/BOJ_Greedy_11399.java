import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class BOJ_Greedy_11399 {

    private static Scanner sc = new Scanner(System.in);
    public static void main(String args[]) {
        int nums = sc.nextInt();
        ArrayList<Integer> times = new ArrayList<>();

        for (int i=0; i<nums; i++) {
            times.add(sc.nextInt());
        }

        Collections.sort(times);

        int sum = 0;
        int result = 0;
        for (int i=0; i<times.size(); i++) {
            sum += times.get(i);
            result += sum;
        }

        System.out.println(result);
    }
}
