package backjoon.first;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon2343 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] lessons = new int[n];

        int left = 0;
        int right = 0;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int lesson = Integer.parseInt(st.nextToken());
            lessons[i] = lesson;
            left = Math.max(left, lesson);
            right += lesson;
        }

        while (left <= right) {
            int mid = (left + right) / 2;

            int count = getCount(n, lessons, mid);

            if (count > m) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        bw.write(Integer.toString(left));
        bw.flush();
    }

    private static int getCount(final int n, final int[] lessons, final int mid) {
        int sum = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (sum + lessons[i] > mid) {
                count++;
                sum = 0;
            }
            sum += lessons[i];
        }
        if (sum > 0) {
            count++;
        }
        return count;
    }
}
