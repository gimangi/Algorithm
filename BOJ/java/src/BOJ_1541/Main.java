package BOJ_1541;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        List<Integer> numbers = Arrays.stream(line.split("-|[+]"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int firstMinusIdx = line.indexOf("-");

        String beforeMinus = line;
        String afterMinus = "";
        if (firstMinusIdx >= 0) {
             beforeMinus = line.substring(0, firstMinusIdx);
             afterMinus = line.substring(firstMinusIdx + 1);
        }

        int answer = 0;
        List<Integer> beforeNums = Arrays.stream(beforeMinus.split("[+]"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> afterNums = Collections.emptyList();

        if (firstMinusIdx >= 0) {
            afterNums = Arrays.stream(afterMinus.split("-|[+]"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        for (int num : beforeNums)
            answer += num;
        for (int num : afterNums)
            answer -= num;

        System.out.println(answer);
    }
}
