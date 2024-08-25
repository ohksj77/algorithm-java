package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon20366 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int n = Integer.parseInt(br.readLine());
        final int[] arr =
                Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int answer = Integer.MAX_VALUE;

        Arrays.sort(arr);

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 3; j < n; j++) {
                int left = i + 1;
                int right = j - 1;
                while (left < right) {
                    final int sum = arr[i] + arr[j] - arr[left] - arr[right];
                    answer = Math.min(answer, Math.abs(sum));
                    if (sum < 0) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }
}
