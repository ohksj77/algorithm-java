package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class 최소_횟수 {

    private static int a;
    private static int b;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] input = br.readLine().split(" ");
        a = Integer.parseInt(input[0]);
        b = Integer.parseInt(input[1]);

        bw.write(Integer.toString(bfs()));
        bw.flush();
    }

    private static int bfs() {
        final Queue<int[]> queue = new LinkedList<>();
        final Set<Integer> visited = new HashSet<>();

        queue.add(new int[] {a, 0});
        visited.add(a);

        while (!queue.isEmpty()) {
            final int[] current = queue.poll();
            final int currentValue = current[0];
            final int operations = current[1];

            if (currentValue == b) {
                return operations;
            }

            int[] nextValues = {currentValue * 2, currentValue - 1, currentValue + 1};
            for (int i = 0; i < 3; i++) {
                final int nextValue = nextValues[i];
                if (nextValue >= 0 && nextValue <= 100000 && !visited.contains(nextValue)) {
                    visited.add(nextValue);
                    if (i == 0) {
                        queue.add(new int[] {nextValue, operations});
                        continue;
                    }
                    queue.add(new int[] {nextValue, operations + 1});
                }
            }
        }

        return -1;
    }
}
