package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class 숫자_퍼즐_모으기 {

    private static final Map<Integer, List<Integer>> PUZZLE_MAP = new HashMap<>();
    private static final Set<Integer> PUZZLE_SET = new HashSet<>();
    private static int min = Integer.MAX_VALUE;
    private static int n;
    private static int m;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        for (int i = 0; i < n; i++) {
            final int[] puzzle =
                    Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < m; j++) {
                final int value = puzzle[j];
                final List<Integer> list = PUZZLE_MAP.getOrDefault(i, new ArrayList<>());
                list.add(value);
                PUZZLE_MAP.put(i, list);
                PUZZLE_SET.add(value);
            }
        }

        find(0, new ArrayList<>());

        bw.write(Integer.toString(min));
        bw.flush();
    }

    private static void find(final int index, final List<Integer> path) {
        if (path.size() > 0) {
            final Set<Integer> allValues = new HashSet<>();
            for (final int value : path) {
                allValues.addAll(PUZZLE_MAP.get(value));
            }
            if (allValues.size() == PUZZLE_SET.size()) {
                min = Math.min(min, path.size());
                return;
            }
        }

        for (int i = index; i < n; i++) {
            path.add(i);
            find(i + 1, path);
            path.remove(path.size() - 1);
        }
    }
}
