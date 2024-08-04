package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;

public class 회의실_준비하기 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int n = Integer.parseInt(br.readLine());

        final Queue<int[]> pq =
                new PriorityQueue<>(
                        (a, b) -> {
                            if (a[0] == b[0]) {
                                return a[1] - b[1];
                            }
                            return a[0] - b[0];
                        });

        int result = 0;

        for (int i = 0; i < n; i++) {
            final String[] input = br.readLine().split(" ");
            pq.add(new int[] {Integer.parseInt(input[0]), Integer.parseInt(input[1])});
        }

        int lastEnd = 0;
        for (int i = 0; i < n; i++) {
            final int[] now = pq.poll();
            final int start = now[0];
            final int end = now[1];

            if (lastEnd <= start) {
                lastEnd = end;
                result++;
                continue;
            }
            lastEnd = Math.min(lastEnd, end);
        }

        bw.write(result);
        bw.flush();
    }
}
