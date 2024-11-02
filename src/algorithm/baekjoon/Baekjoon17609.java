package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon17609 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            int result = palindrome(0, input.length() - 1, input, 0);

            bw.write(Integer.toString(result));
            bw.newLine();
        }
        bw.flush();
    }

    private static int palindrome(int start, int end, String str, int count) {
        if (count >= 2) {
            return 2;
        }
        while (start < end) {
            if (str.charAt(start) == str.charAt(end)) {
                start++;
                end--;
            } else {
                return Math.min(
                        palindrome(start + 1, end, str, count + 1),
                        palindrome(start, end - 1, str, count + 1));
            }
        }
        return count;
    }
}
