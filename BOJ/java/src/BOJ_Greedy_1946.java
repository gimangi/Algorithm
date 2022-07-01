import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_Greedy_1946 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException {

        int T = Integer.parseInt(br.readLine());

        while (--T >= 0) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<Person> list = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                String[] splitLine = line.split(" ");

                int first = Integer.parseInt(splitLine[0]);
                int second = Integer.parseInt(splitLine[1]);
                list.add(new Person(first, second));
            }

            // 서류 순 정렬
            Collections.sort(list, (p1, p2) -> p1.first - p2.first);

            Person last = list.get(0);
            int count = 1;
            for (int i=1; i<list.size(); i++) {
                if (last.second > list.get(i).second) {
                    last = list.get(i);
                    count++;
                }
            }

            System.out.println(count);
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
