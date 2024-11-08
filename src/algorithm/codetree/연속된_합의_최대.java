package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 연속된_합의_최대 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        int[] arr = new int[n];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        int[] dp = new int[n];

        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        dp[k - 1] = sum;
        int maxSum = sum;

        for (int i = k; i < n; i++) {
            sum += arr[i];
            sum -= arr[i - k];
            dp[i] = Math.max(dp[i - 1] + arr[i], sum);
            maxSum = Math.max(maxSum, dp[i]);
        }

        bw.write(Integer.toString(maxSum));
        bw.flush();
    }
}
