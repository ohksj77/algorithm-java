package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 점수_뽑기 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int[][] scores = new int[4][N];
        for (int i = 0; i < 4; i++) {
            scores[i] =
                    Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        final List<Integer> sum1 = new ArrayList<>();
        final List<Integer> sum2 = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum1.add(scores[0][i] + scores[1][j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum2.add(scores[2][i] + scores[3][j]);
            }
        }

        Collections.sort(sum2);

        int count = 0;

        for (int s1 : sum1) {
            int target = K - s1;
            count += upperBound(sum2, target) - lowerBound(sum2, target);
        }

        bw.write(Integer.toString(count));
        bw.flush();
    }

    private static int lowerBound(final List<Integer> list, final int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private static int upperBound(final List<Integer> list, final int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid) <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
