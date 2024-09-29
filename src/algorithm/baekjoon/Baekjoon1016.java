package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1016 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] input = br.readLine().split(" ");

        final long min = Long.parseLong(input[0]);
        final long max = Long.parseLong(input[1]);

        final boolean[] isNotPrime = new boolean[(int) (max - min + 1)];
        for (long i = 2; i * i <= max; i++) {
            final long start = min % (i * i) == 0 ? min / (i * i) : min / (i * i) + 1;
            for (long j = start; i * i * j <= max; j++) {
                isNotPrime[(int) (i * i * j - min)] = true;
            }
        }

        int count = 0;
        for (final boolean b : isNotPrime) {
            if (!b) {
                count++;
            }
        }

        bw.write(Integer.toString(count));
        bw.flush();
    }
}
