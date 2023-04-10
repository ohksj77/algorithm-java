package backjoon.study.third;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon2110 {
    private static int[] list;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        list = new int[n];

        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(list);

        int low = 1, high = list[n - 1] - list[0] + 1;

        while (low < high) {
            int middle = (high + low) / 2;
            if (numOfInstallable(middle) < c) {
                high = middle;
            } else {
                low = middle + 1;
            }
        }
        bw.write(String.valueOf(low - 1));
        bw.flush();
    }
    private static int numOfInstallable(int distance) {
        int count = 1;
        int last = list[0];

        for (int i = 1; i < list.length; i++) {
            int locate = list[i];
            if (locate - last >= distance) {
                count++;
                last = locate;
            }
        }
        return count;
    }
}
