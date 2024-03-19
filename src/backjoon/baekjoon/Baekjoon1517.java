package backjoon.baekjoon;

import java.io.*;

public class Baekjoon1517 {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        String[] line = br.readLine().split(" ");
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(line[i]);

        bw.write(String.valueOf(solve(a, 0, n - 1)));
        bw.flush();
    }

    private static long solve(int[] a, int start, int end) {
        if (start == end) return 0L;

        int middle = (start + end) / 2;
        int[] b = new int[end - start + 1];

        long answer = solve(a, start, middle) + solve(a, middle + 1, end);

        int i = start, j = middle + 1, k = 0;

        while (i <= middle || j <= end) {
            if (i <= middle && (j > end || a[i] <= a[j])) {
                b[k++] = a[i++];
            } else {
                answer += (middle - i + 1);
                b[k++] = a[j++];
            }
        }
        for (int index = start; index <= end; index++) a[index] = b[index - start];
        return answer;
    }
}
