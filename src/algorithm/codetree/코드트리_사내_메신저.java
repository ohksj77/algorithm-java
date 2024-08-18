package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 코드트리_사내_메신저 {

    private static final List<List<Integer>> ADJACENT = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int n = Integer.parseInt(br.readLine());
        final String[] input = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            ADJACENT.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            final int boss = Integer.parseInt(input[i]);
            ADJACENT.get(boss - 1).add(i);
        }

        bw.write(Integer.toString(dfs(0)));
        bw.flush();
    }

    private static int dfs(final int node) {
        final List<Integer> times = new ArrayList<>();
        for (final int next : ADJACENT.get(node)) {
            times.add(dfs(next));
        }

        if (times.isEmpty()) {
            return 0;
        }

        times.sort(Collections.reverseOrder());

        int maxTime = 0;
        for (int i = 0; i < times.size(); i++) {
            maxTime = Math.max(maxTime, times.get(i) + (i + 1));
        }

        return maxTime;
    }
}
