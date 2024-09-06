package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon3020 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));

        final StringTokenizer st = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(st.nextToken());
        final int h = Integer.parseInt(st.nextToken());

        final int[] top = new int[h + 1];
        final int[] bottom = new int[h + 1];

        for (int i = 0; i < n / 2; i++) {
            bottom[Integer.parseInt(br.readLine())]++;
            top[Integer.parseInt(br.readLine())]++;
        }

        for (int i = h - 1; i > 0; i--) {
            bottom[i] += bottom[i + 1];
            top[i] += top[i + 1];
        }

        int min = Integer.MAX_VALUE;
        int count = 0;

        for (int i = 1; i <= h; i++) {
            final int sum = bottom[i] + top[h - i + 1];
            if (sum < min) {
                min = sum;
                count = 1;
                continue;
            }
            if (sum == min) {
                count++;
            }
        }

        bw.write(min + " " + count);
        bw.flush();
    }
}
