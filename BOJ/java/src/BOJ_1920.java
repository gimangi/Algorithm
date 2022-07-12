import java.util.HashMap;
import java.util.Scanner;

public class BOJ_1920 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        int N = sc.nextInt();
        for (int i=0; i<N; i++)
            map.put(sc.nextInt(), true);

        int M = sc.nextInt();
        for (int i=0; i<M; i++) {
            int num = sc.nextInt();
            if (map.get(num) != null)
                System.out.println(1);
            else
                System.out.println(0);
        }
    }
}
