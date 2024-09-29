package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 건물_짓는_시간 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int n = Integer.parseInt(br.readLine());

        final int[] buildTime = new int[n + 1];
        final List<List<Integer>> graph = new ArrayList<>();
        final int[] inDegree = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            final String[] input = br.readLine().split(" ");
            buildTime[i] = Integer.parseInt(input[0]);
            final int value = Integer.parseInt(input[1]);

            for (int j = 0; j < value; j++) {
                final int now = Integer.parseInt(input[j + 2]);
                graph.get(now).add(i);
                inDegree[i]++;
            }
        }

        final int result = find(n, buildTime, graph, inDegree);
        bw.write(Integer.toString(result));
        bw.flush();
    }

    private static int find(
            final int n,
            final int[] buildTime,
            final List<List<Integer>> graph,
            final int[] inDegree) {
        final Queue<Integer> queue = new LinkedList<>();
        final int[] totalTime = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                totalTime[i] = buildTime[i];
            }
        }

        while (!queue.isEmpty()) {
            final int current = queue.poll();

            for (final int next : graph.get(current)) {
                totalTime[next] = Math.max(totalTime[next], totalTime[current] + buildTime[next]);
                if (--inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        int maxTime = 0;
        for (final int time : totalTime) {
            maxTime = Math.max(maxTime, time);
        }

        return maxTime;
    }
}
