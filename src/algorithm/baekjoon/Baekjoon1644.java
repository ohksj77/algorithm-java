package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Baekjoon1644 {

    public static void main(String[] args) throws Exception {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int n = Integer.parseInt(br.readLine());
        final boolean[] prime = new boolean[n + 1];

        findPrime(n, prime);

        final List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (!prime[i]) {
                primes.add(i);
            }
        }

        final int size = primes.size();

        int left = 0;
        int right = 0;
        int sum = 0;
        int count = 0;

        while (true) {
            if (sum >= n) {
                sum -= primes.get(left++);
            } else if (right == size) {
                break;
            } else {
                sum += primes.get(right++);
            }
            if (sum == n) {
                count++;
            }
        }

        bw.write(Integer.toString(count));
        bw.flush();
    }

    private static void findPrime(final int n, final boolean[] prime) {
        for (int i = 2; i * i <= n; i++) {
            if (!prime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    prime[j] = true;
                }
            }
        }
    }
}
