package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Baekjoon10800 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        List<int[]> balls = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            balls.add(new int[] {i, Integer.parseInt(input[0]), Integer.parseInt(input[1])});
        }

        balls.sort(Comparator.comparingInt(o -> o[2]));

        int[] answer = new int[n];

        int sum = 0;
        int[] color = new int[n + 1];

        for (int i = 0, j = 0; i < n; i++) {
            int[] a = balls.get(i);
            int[] b = balls.get(j);

            while (b[2] < a[2]) {
                sum += b[2];
                color[b[1]] += b[2];

                b = balls.get(++j);
            }

            answer[a[0]] = sum - color[a[1]];
        }

        for (int a : answer) {
            bw.write(Integer.toString(a));
            bw.newLine();
        }

        bw.flush();
    }
}
