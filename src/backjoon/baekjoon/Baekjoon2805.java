package backjoon.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon2805 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] array = new int[N];

        int min = 0;
        int max = 0;

        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());

            if (max < array[i]) {
                max = array[i];
            }
        }

        while (min < max) {

            int mid = (min + max) / 2;
            long sum = 0;
            for (final int height : array) {
                if (height - mid > 0) {
                    sum += height - mid;
                }
            }
            if (sum < M) {
                max = mid;
                continue;
            }
            min = mid + 1;
        }
        System.out.println(min - 1);
    }
}
