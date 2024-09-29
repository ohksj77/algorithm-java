package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Baekjoon23059 {

    private static final Map<String, List<String>> RELATION = new HashMap<>();
    private static final Map<String, Integer> IN_DEGREE = new HashMap<>();
    private static final StringBuilder SB = new StringBuilder();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            final String[] input = br.readLine().split(" ");
            final String prev = input[0];
            final String next = input[1];

            if (!RELATION.containsKey(prev)) {
                RELATION.put(prev, new ArrayList<>());
                IN_DEGREE.put(prev, 0);
            }

            if (!IN_DEGREE.containsKey(next)) {
                RELATION.put(next, new ArrayList<>());
                IN_DEGREE.put(next, 0);
            }

            RELATION.get(prev).add(next);
            IN_DEGREE.put(next, IN_DEGREE.get(next) + 1);
        }

        solution();

        boolean flag = true;
        for (final String key : IN_DEGREE.keySet()) {
            if (IN_DEGREE.get(key) != 0) {
                flag = false;
                break;
            }
        }

        bw.write(flag ? SB.toString() : "-1");
        bw.flush();
    }

    private static void solution() {
        final Queue<String> q = new PriorityQueue<>();

        for (final String key : IN_DEGREE.keySet()) {
            if (IN_DEGREE.get(key) == 0) {
                q.add(key);
            }
        }

        while (!q.isEmpty()) {
            final int size = q.size();
            final Queue<String> sortStr = new PriorityQueue<>();

            for (int i = 0; i < size; i++) {
                final String now = q.poll();
                SB.append(now).append("\n");

                for (final String next : RELATION.get(now)) {
                    IN_DEGREE.put(next, IN_DEGREE.get(next) - 1);

                    if (IN_DEGREE.get(next) == 0) {
                        sortStr.add(next);
                    }
                }
            }

            q.addAll(sortStr);
        }
    }
}
