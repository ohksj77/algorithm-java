package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class 멀리멀리 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        String[] input = br.readLine().split(" ");

        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(numbers);

        bw.write(Integer.toString(calculate(numbers, m)));
        bw.flush();
    }

    private static int calculate(int[] numbers, int m) {
        int left = 1;
        int right = numbers[numbers.length - 1] - numbers[0];
        int result = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isOk(numbers, m, mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    private static boolean isOk(int[] numbers, int m, int distance) {
        int count = 1;
        int lastPosition = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] - lastPosition >= distance) {
                count++;
                lastPosition = numbers[i];
                if (count >= m) {
                    return true;
                }
            }
        }
        return false;
    }
}
