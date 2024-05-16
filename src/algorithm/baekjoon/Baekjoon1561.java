package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon1561 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] times = new int[m];

        st = new StringTokenizer(br.readLine());

        int max = 0;
        for (int i = 0; i < m; i++) {
            times[i] = Integer.parseInt(st.nextToken());
            if (max < times[i]) max = times[i];
        }

        long s = 0;
        long e = (n / m + 1) * max;

        while (s <= e) {
            long mid = (s + e) >> 1;
            long cap = getCapacity(times, mid);
            if (cap < n) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        long cap = getCapacity(times, s);
        long index = cap - n;
        int answer = 0;

        for (int i = times.length - 1; i >= 0; i--) {
            if (s % times[i] == 0) {
                if (index == 0) {
                    answer = i + 1;
                    break;
                }
                index--;
            }
        }
        bw.write(Integer.toString(answer));
        bw.flush();
    }

    public static long getCapacity(int[] times, long t) {
        long capacity = 0;
        for (final int time : times) {
            capacity += t / time + 1;
        }
        return capacity;
    }
}
