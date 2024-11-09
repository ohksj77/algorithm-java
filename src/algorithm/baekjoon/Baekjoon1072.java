package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1072 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        long x = Long.parseLong(input[0]);
        long y = Long.parseLong(input[1]);

        long z = y * 100 / x;

        if (z >= 99) {
            bw.write("-1");
        } else {
            long left = 0;
            long right = 1_000_000_000;
            long result = 0;

            while (left <= right) {
                long mid = (left + right) / 2;
                long temp = (y + mid) * 100 / (x + mid);

                if (temp > z) {
                    result = mid;
                    right = mid - 1;
                    continue;
                }
                left = mid + 1;
            }

            bw.write(Long.toString(result));
        }
        bw.flush();
    }
}
