package BOJ_1043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N, M;
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        Queue<Person> detectors = new LinkedList<>();

        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person(0)); // dummy
        // init
        for (int i=1; i<=N; i++) {
            Person person = new Person(i);
            persons.add(person);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        for (int i=0; i<k; i++) {
            Person person = persons.get(Integer.parseInt(st.nextToken()));
            detectors.add(person);
        }


        ArrayList<Person>[] allParties = new ArrayList[M];
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            allParties[i] = new ArrayList<>();

            for (int j=0; j<n; j++) {
                int participant = Integer.parseInt(st.nextToken());
                Person person = persons.get(participant);
                person.parties.add(i);
                allParties[i].add(person);
            }
        }

        boolean[] haveDetector = new boolean[M];

        while (!detectors.isEmpty()) {
            Person detector = detectors.poll();

            for (int party : detector.parties) {
                if (haveDetector[party])
                    continue;

                haveDetector[party] = true;

                for (Person person : allParties[party]) {
                    detectors.offer(person);
                }
            }
        }

        int ans = 0;
        for (int i=0; i<M; i++) {
            if (!haveDetector[i])
                ans++;
        }

        System.out.println(ans);
    }
}

class Person {
    int id;
    ArrayList<Integer> parties = new ArrayList<>();

    Person(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ""+id;
    }
}