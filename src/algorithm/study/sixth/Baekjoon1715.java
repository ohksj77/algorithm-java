package algorithm.study.sixth;

import java.io.*;
import java.util.PriorityQueue;

public class Baekjoon1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int sum = 0;

        while (pq.size() > 1) {
            int tmp = pq.poll() + pq.poll();
            sum += tmp;
            pq.add(tmp);
        }

        bw.write(Integer.toString(sum));
        bw.flush();
    }
}
