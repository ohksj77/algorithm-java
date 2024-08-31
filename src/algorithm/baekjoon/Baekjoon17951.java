package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon17951 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] nk = br.readLine().split(" ");
        final int n = Integer.parseInt(nk[0]);
        final int k = Integer.parseInt(nk[1]);
        final int[] arr = new int[n];
        int left = 0;
        int right = 0;

        final String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
            right += arr[i];
        }

        int answer = 0;
        while (left <= right) {
            final int mid = (left + right) / 2;
            int sum = 0;
            int count = 0;
            for (int i = 0; i < n; i++) {
                sum += arr[i];
                if (sum >= mid) {
                    sum = 0;
                    count++;
                }
            }
            if (count >= k) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }
}
