package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Baekjoon15686 {

    private static final List<int[]> CHICKENS = new ArrayList<>();
    private static final List<int[]> HOUSES = new ArrayList<>();
    private static int n;
    private static int m;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw =
                new BufferedWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                final int now = Integer.parseInt(st.nextToken());
                if (now == 1) {
                    HOUSES.add(new int[] {i, j});
                } else if (now == 2) {
                    CHICKENS.add(new int[] {i, j});
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        final Set<Set<int[]>> points = new HashSet<>();

        if (CHICKENS.size() != m) {
            combination(0, 0, new ArrayList<>(), points);
        } else {
            points.add(new HashSet<>(CHICKENS));
        }

        for (final Set<int[]> point : points) {
            answer = Math.min(answer, getMinDistance(point));
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }

    private static int getMinDistance(final Set<int[]> points) {
        int sum = 0;
        for (int[] house : HOUSES) {
            int min = Integer.MAX_VALUE;
            for (int[] point : points) {
                min = Math.min(min, getDistance(house[0], house[1], point[0], point[1]));
            }
            sum += min;
        }
        return sum;
    }

    private static void combination(
            final int depth,
            final int index,
            final List<int[]> path,
            final Set<Set<int[]>> result) {
        if (depth == m) {
            result.add(new HashSet<>(path));
            return;
        }
        for (int i = index; i < CHICKENS.size(); i++) {
            final int[] point = CHICKENS.get(i);
            if (path.contains(point)) {
                continue;
            }
            path.add(point);
            combination(depth + 1, index + 1, path, result);
            path.remove(path.size() - 1);
        }
    }

    private static int getDistance(final int x1, final int y1, final int x2, final int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
