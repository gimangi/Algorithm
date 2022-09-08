package BOJ_9466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            String[] line = br.readLine().split(" ");
            Student[] students = new Student[N + 1];

            for (int i = 1; i <= N; i++)
                students[i] = new Student(i);

            for (int i = 0; i < N; i++) {
                int l = Integer.parseInt(line[i]);
                students[i + 1].link = students[l];
                students[l].inDegree++;
            }
            System.out.println(sizeOfAcycles(students));
        }
        br.close();
    }

    static int sizeOfAcycles(Student[] students) {
        int ret = 0;
        Queue<Student> queue = new LinkedList<>();
        for (int i = 1; i < students.length; i++)
            if (students[i].inDegree == 0)
                queue.add(students[i]);

        while (!queue.isEmpty()) {
            Student cur = queue.poll();
            ret++;

            if (--cur.link.inDegree == 0)
                queue.add(cur.link);
        }

        return ret;
    }

    static class Student {
        int num;
        Student link;
        int inDegree = 0;

        Student(int num) {
            this.num = num;
        }
    }
}
