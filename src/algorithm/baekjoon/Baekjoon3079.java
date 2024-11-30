package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon3079 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        long[] times = new long[n];
        long max = 0;
        for (int i = 0; i < n; i++) {
            times[i] = Long.parseLong(br.readLine());
            max = Math.max(max, times[i]);
        }

        Arrays.sort(times);

        bw.write(Long.toString(solve(times, m, max)));
        bw.flush();
    }

    private static long solve(long[] times, int m, long max) {
        long low = 0;
        long high = m * max;

        long result = Long.MAX_VALUE;

        while (low <= high) {
            long mid = (low + high) / 2;
            long sum = 0;
            for (long index : times) {
                long count = mid / index;

                if (sum >= m) {
                    break;
                }
                sum += count;
            }
            if (sum >= m) {
                high = mid - 1;
                result = Math.min(mid, result);
            } else {
                low = mid + 1;
            }
        }
        return result;
    }
}
