package backjoon.first;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class Baekjoon6588 {

    private static int n = 1000000;
    private static boolean[] prime = new boolean[n];

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        find();
        while (true) {
            int i = Integer.parseInt(br.readLine());
            if (i == 0) {
                break;
            }
            boolean flag = true;
            for (int a = 3; a < n; a++) {
                if (prime[a] && prime[i - a]) {
                    System.out.println(i + " = " + a + " + " + (i - a));
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println("Goldbach's conjecture is wrong.");
            }
        }
    }

    private static void find() {
        Arrays.fill(prime, true);
        for (int i = 2; i < Math.pow(n, 0.5) + 1; i++) {
            if (prime[i]) {
                int tempI = i;
                Stream.iterate(2 * i, a -> a < n, a -> a + tempI).forEach(j -> prime[j] = false);
            }
        }
    }
}
