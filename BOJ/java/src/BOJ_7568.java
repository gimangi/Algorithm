import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_7568 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        int N = sc.nextInt();
        ArrayList<Person> list = new ArrayList<>();

        for (int i=0; i<N; i++) {
            int w = sc.nextInt();
            int h = sc.nextInt();
            Person p = new Person(w, h);
            list.add(p);
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (list.get(i).weight<list.get(j).weight && list.get(i).height<list.get(j).height)
                    list.get(i).rank++;
            }
        }

        for (int i=0; i<N; i++)
            System.out.print(list.get(i).rank+" ");
    }

    static class Person {
        int weight;
        int height;

        int rank = 1;

        Person(int weight, int height) {
            this.weight = weight;
            this.height = height;
        }
    }

}
