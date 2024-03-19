package backjoon.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon13305 {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        long n, sum = 0, x, tmp;
        long[] arr = new long[100001];
        n = Long.parseLong(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        tmp = Long.parseLong(st.nextToken());

        for (int i = 1; i < n; i++) {
            sum = sum + tmp * arr[i - 1];
            x = Long.parseLong(st.nextToken());
            tmp = tmp < x ? tmp : x;
        }
        bw.write(String.valueOf(sum));
        bw.flush();
    }
}
