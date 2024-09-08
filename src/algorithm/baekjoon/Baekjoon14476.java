package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon14476 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int n = Integer.parseInt(br.readLine());
        final int[] arr = new int[n];
        final int[] gcdLeft = new int[n];
        final int[] gcdRight = new int[n];
        final int[] input =
                Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        arr[0] = input[0];
        for (int i = 1; i < n; i++) {
            arr[i] = input[i];
            gcdLeft[i] = gcd(gcdLeft[i - 1], arr[i - 1]);
        }

        arr[n - 1] = input[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            arr[i] = input[i];
            gcdRight[i] = gcd(gcdRight[i + 1], arr[i + 1]);
        }

        int answer = -1;
        int idx = -1;
        for (int i = 0; i < n; i++) {
            final int gcd = gcd(gcdLeft[i], gcdRight[i]);
            if (arr[i] % gcd != 0 && gcd > answer) {
                answer = gcd;
                idx = i;
            }
        }

        if (answer == -1) {
            bw.write("-1");
        } else {
            bw.write(answer + " " + arr[idx]);
        }

        bw.flush();
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
