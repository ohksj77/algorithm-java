package algorithm.elice_challenge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Day1 {
    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final String input = br.readLine();

        bw.write(solve(input));
        bw.flush();
    }

    private static String solve(final String input) {
        final StringBuilder sb = new StringBuilder();

        final int length = input.length();
        final char[] digits = input.toCharArray();

        for (int i = length - 1; i >= 1; i--) {
            int point = digits[i] - '0';

            if (digits[i - 1] - '0' < point) {
                int target = digits[i - 1] - '0';

                StringBuilder tempSb = new StringBuilder();
                for (int j = i; j < length; j++) {
                    tempSb.append(digits[j]);
                }

                char[] tempDigits = tempSb.toString().toCharArray();
                Arrays.sort(tempDigits);
                int len = tempDigits.length;

                for (int j = 0; j < len; j++) {
                    if (tempDigits[j] - '0' > target) {
                        char temp = digits[i - 1];
                        digits[i - 1] = tempDigits[j];
                        tempDigits[j] = temp;
                        break;
                    }
                }

                for (int j = 0; j <= i - 1; j++) {
                    sb.append(digits[j]);
                }

                for (final char c : tempDigits) {
                    sb.append(c);
                }
                break;
            }
        }

        return sb.toString();
    }
}
