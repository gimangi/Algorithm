import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_Greedy_1946 {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        int T = sc.nextInt();

        while (--T >= 0) {
            int N = sc.nextInt();
            ArrayList<Person> list = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                int first = sc.nextInt();
                int second = sc.nextInt();

                list.add(new Person(first, second));
            }

            // 서류
            Collections.sort(list, (p1, p2) -> p1.first - p2.first);
            for (int i=0; i<list.size()-1; i++) {
                if (list.get(i).second < list.get(i+1).second) {
                    list.remove(list.get(i+1));
                }
            }

            // 면접
            Collections.sort(list, (p1, p2) -> p1.second - p2.second);
            for (int i=0; i<list.size()-1; i++) {
                if (list.get(i).first < list.get(i+1).first) {
                    list.remove(list.get(i));
                }
            }
            System.out.println(list.size());

        }

    }
}

class Person{
    int first;
    int second;
    Person(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
