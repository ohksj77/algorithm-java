package backjoon.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon10942 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        br.readLine();
        final int[] nums =
                Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        final int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            final String[] input = br.readLine().split(" ");
            final int start = Integer.parseInt(input[0]);
            final int end = Integer.parseInt(input[1]);
            bw.write(isPalindrome(nums, start - 1, end - 1) ? "1" : "0");
            bw.newLine();
        }

        bw.flush();
    }

    private static boolean isPalindrome(final int[] nums, int start, int end) {
        while (start < end) {
            if (nums[start++] != nums[end--]) {
                return false;
            }
        }
        return true;
    }
}
