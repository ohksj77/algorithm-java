package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;

public class 최대_숫자_생성 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        Queue<String> pq = new PriorityQueue<>((a, b) -> (b + a).compareTo(a + b));

        for (int i = 0; i < n; i++) {
            pq.offer(br.readLine());
        }

        while (!pq.isEmpty()) {
            bw.write(pq.poll());
        }
        bw.flush();
    }
}
