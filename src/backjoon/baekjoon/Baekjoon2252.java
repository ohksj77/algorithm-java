package backjoon.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Baekjoon2252 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] nm = br.readLine().split(" ");
        final int n = Integer.parseInt(nm[0]);
        final int m = Integer.parseInt(nm[1]);

        final int[] indegree = new int[n + 1];
        final List<Integer>[] list = new LinkedList[n + 1];

        for (int i = 0; i < m; i++) {
            final String[] ab = br.readLine().split(" ");
            final int a = Integer.parseInt(ab[0]);
            final int b = Integer.parseInt(ab[1]);

            if (list[a] == null) {
                list[a] = new LinkedList<>();
            }
            list[a].add(b);
            indegree[b]++;
        }

        final Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                indegree[i]--;
                bw.write(i + " ");
            }
        }

        while (!queue.isEmpty()) {
            final int current = queue.poll();
            final List<Integer> now = list[current];

            if (now != null) {
                for (final int next : now) {
                    indegree[next]--;
                    if (indegree[next] == 0) {
                        queue.add(next);
                        bw.write(next + " ");
                    }
                }
            }
        }

        bw.flush();
    }
}
