package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon17179 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nml = br.readLine().split(" ");

        int n = Integer.parseInt(nml[0]);
        int m = Integer.parseInt(nml[1]);
        int l = Integer.parseInt(nml[2]);

        int[] cakes = new int[m + 1];
        for (int i = 0; i < m; i++) {
            cakes[i] = Integer.parseInt(br.readLine());
        }
        cakes[m] = l;

        for (int i = 0; i < n; i++) {
            int answer = 0;
            int q = Integer.parseInt(br.readLine());
            int low = 0;
            int high = l;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (checkCake(mid, cakes, q)) {
                    low = mid + 1;
                    answer = Math.max(mid, answer);
                } else {
                    high = mid - 1;
                }
            }
            bw.write(Integer.toString(answer));
            bw.newLine();
        }
        bw.flush();
    }

    public static boolean checkCake(int mid, int[] cakes, int q) {
        int prev = 0;
        for (final int cake : cakes) {
            if (cake - prev >= mid) {
                q--;
                prev = cake;
            }
        }
        return q < 0;
    }
}
