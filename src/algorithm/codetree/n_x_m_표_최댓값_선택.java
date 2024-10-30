package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class n_x_m_표_최댓값_선택 {

    private static final Map<Integer, List<Integer>> ROW_MAP = new HashMap<>();
    private static final Map<Integer, List<Integer>> COL_MAP = new HashMap<>();
    private static int n;
    private static int m;
    private static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                int value = Integer.parseInt(line[j]);
                List<Integer> row = ROW_MAP.getOrDefault(i, new ArrayList<>());
                row.add(value);
                ROW_MAP.put(i, row);

                List<Integer> col = COL_MAP.getOrDefault(j, new ArrayList<>());
                col.add(value);
                COL_MAP.put(j, col);
            }
        }
        three(new HashSet<>(), 0);

        bw.write(Integer.toString(max));
        bw.flush();
    }

    private static void three(Set<Integer> path, int index) {
        if (path.size() == 3) {
            List<List<Integer>> lines = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                if (path.contains(i)) {
                    continue;
                }
                List<Integer> values = COL_MAP.get(i);
                lines.add(values);
            }

            int sum = 0;
            for (int i = 0; i < n; i++) {
                int maxValue = 0;
                for (int j = 0; j < m - 3; j++) {
                    maxValue = Math.max(maxValue, lines.get(j).get(i));
                }
                sum += maxValue;
            }
            max = Math.max(max, sum);
            return;
        }

        for (int i = index; i < m; i++) {
            path.add(i);
            three(path, i + 1);
            path.remove(i);
        }
    }
}
