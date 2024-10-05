package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon1477 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final String[] nml = br.readLine().split(" ");

        int n = Integer.parseInt(nml[0]);
        int m = Integer.parseInt(nml[1]);
        int l = Integer.parseInt(nml[2]);
        String[] input = br.readLine().split(" ");
        int[] arr = new int[n + 2];

        arr[0] = 0;
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(input[i - 1]);
        }
        arr[n + 1] = l;
        Arrays.sort(arr);

        int left = 1;
        int right = l - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int sum = 0;

            for (int i = 1; i < arr.length; i++) {
                sum += (arr[i] - arr[i - 1] - 1) / mid;
            }

            if (sum > m) {
                left = mid + 1;
                continue;
            }
            right = mid - 1;
        }
        bw.write(Integer.toString(left));
        bw.flush();
    }
}
