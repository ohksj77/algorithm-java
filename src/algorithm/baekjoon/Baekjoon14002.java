package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Baekjoon14002 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        int[] dp = new int[n];
        List<List<Integer>> array = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            array.add(new ArrayList<>(List.of(numbers[i])));
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (numbers[i] > numbers[j] && dp[i] < dp[j]) {
                    array.set(i, new ArrayList<>(array.get(j)));
                    array.get(i).add(numbers[i]);
                    dp[i] = dp[j];
                }
            }
            dp[i]++;
        }

        int max = 0;
        int maxIndex = 0;

        for (int i = 0; i < n; i++) {
            if (max < dp[i]) {
                maxIndex = i;
                max = dp[i];
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(max).append("\n");

        for (int i = 0; i < max; i++) {
            sb.append(array.get(maxIndex).get(i)).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
