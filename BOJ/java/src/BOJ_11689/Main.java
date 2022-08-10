package BOJ_11689;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        if (n == 1l) {
            System.out.println(1);
            return;
        }

        ArrayList<Long> temp = new ArrayList<>();
        for (long i=2; i<=Math.sqrt(n); i++) {
            while (n % i == 0) {
                n /= i;
                temp.add(i);
            }
        }
        if (n != 1l)
            temp.add(n);

        ArrayList<Mul> decompose = new ArrayList<>();
        decompose.add(new Mul(temp.get(0)));

        int prev = 0;
        for (int i=1; i<temp.size(); i++) {
            if (temp.get(i-1) == temp.get(i)) {
                decompose.get(prev).increase();
            } else {
                decompose.add(new Mul(temp.get(i)));
                prev += 1;
            }
        }

        long ans = 1;
        for (Mul prime : decompose) {
            ans *= (Math.pow(prime.num, prime.pow) - Math.pow(prime.num, prime.pow - 1));
        }
        System.out.println(ans);
    }

    static class Mul {
        long num;
        int pow = 1;

        Mul(long num) {
            this.num = num;
        }

        void increase() {
            pow += 1;
        }

        @Override
        public String toString() {
            return ""+num + "^" + pow;
        }
    }
}
