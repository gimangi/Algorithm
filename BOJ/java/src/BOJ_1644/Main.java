package BOJ_1644;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        boolean[] isPrime = new boolean[N + 1];
        for (int i=2; i<=N; i++)
            isPrime[i] = true;

        for (int i=2; i*i<=N; i++) {
            if (isPrime[i])
                for (int j=i*i; j<=N; j+=i)
                    isPrime[j] = false;
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i=2; i<=N; i++)
            if (isPrime[i])
                primes.add(i);

        if (primes.size() < 1) {
            System.out.println(0);
            return;
        }

        int cnt = 0;

        int start = 0;
        int end = 0;
        int sum = primes.get(start);

        for (; start < primes.size(); start++) {
            while (sum < N && end < primes.size() - 1)
                sum += primes.get(++end);

            if (sum == N)
                cnt += 1;

            sum -= primes.get(start);
        }

        System.out.println(cnt);
    }
}
