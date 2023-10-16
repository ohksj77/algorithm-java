package backjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1654 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int K = Integer.parseInt(st.nextToken());
        final int N = Integer.parseInt(st.nextToken());

        int[] array = new int[K];

        long min = 0L;
        long mid = 0L;
        long max = 0L;

        for (int i = 0; i < K; i++) {
            array[i] = Integer.parseInt(br.readLine());

            if (max < array[i]) {
                max = array[i];
            }
        }
        max++;

        while (min < max) {
            mid = (max + min) / 2;
            long count = 0;

            for (int i = 0; i < array.length; i++) {
                count += (array[i] / mid);
            }

            if (count < N) {
                max = mid;
                continue;
            }
            min = mid + 1;
        }
        System.out.println(min - 1);
    }
}
