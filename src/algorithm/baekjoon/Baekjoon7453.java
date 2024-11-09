package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon7453 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }

        int[] ab = new int[n * n];
        int[] cd = new int[n * n];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ab[idx] = arr[i][0] + arr[j][1];
                cd[idx++] = arr[i][2] + arr[j][3];
            }
        }

        Arrays.sort(ab);
        Arrays.sort(cd);

        long answer = find(n, ab, cd);

        bw.write(Long.toString(answer));
        bw.flush();
    }

    private static long find(int n, int[] ab, int[] cd) {
        long answer = 0;
        int left = 0;
        int right = n * n - 1;

        while (left < n * n && right >= 0) {
            int sum = ab[left] + cd[right];
            if (sum == 0) {
                long abCount = 0;
                long cdCount = 0;

                int abValue = ab[left];
                int cdValue = cd[right];

                while (left < n * n && ab[left] == abValue) {
                    abCount++;
                    left++;
                }

                while (right >= 0 && cd[right] == cdValue) {
                    cdCount++;
                    right--;
                }

                answer += abCount * cdCount;
            } else if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }
        return answer;
    }
}
