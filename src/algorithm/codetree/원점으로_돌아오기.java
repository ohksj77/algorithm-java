package algorithm.codetree;

import java.io.*;
import java.util.*;
import java.util.List;

public class 원점으로_돌아오기 {

    private static final Map<Integer, List<Point>> X_MAP = new HashMap<>();
    private static final Map<Integer, List<Point>> Y_MAP = new HashMap<>();
    private static final Set<Point> POINTS = new HashSet<>();
    private static final Set<String> PATHS = new HashSet<>();
    private static int n;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            final int[] xy =
                    Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            final int x = xy[0];
            final int y = xy[1];

            final List<Point> xList = X_MAP.getOrDefault(x, new ArrayList<>());
            final List<Point> yList = Y_MAP.getOrDefault(y, new ArrayList<>());
            final Point point = new Point(xy);

            xList.add(point);
            yList.add(point);

            X_MAP.put(x, xList);
            Y_MAP.put(y, yList);

            POINTS.add(point);
        }

        int count = 0;

        if (X_MAP.containsKey(0)) {
            final List<Point> points = X_MAP.get(0);

            for (final Point point : points) {
                final Set<Point> path = new HashSet<>();
                path.add(point);

                count += dfs(point, path, new StringBuilder(point.toString()), point.y > 0 ? 1 : 2);
            }
        }
        if (Y_MAP.containsKey(0)) {
            final List<Point> points = Y_MAP.get(0);

            for (final Point point : points) {
                final Set<Point> path = new HashSet<>();
                path.add(point);

                count += dfs(point, path, new StringBuilder(point.toString()), point.x > 0 ? 3 : 4);
            }
        }

        bw.write(Integer.toString(count));
        bw.flush();
    }

    private static int dfs(
            final Point point,
            final Set<Point> visited,
            final StringBuilder path,
            final int direction) {
        int count = 0;

        if ((point.x == 0 || point.y == 0) && visited.containsAll(POINTS)) {
            if (point.x == 0 && (0 > point.y ? 1 : 2) == direction) {
                return 0;
            }
            if (point.y == 0 && (0 > point.x ? 3 : 4) == direction) {
                return 0;
            }

            if (!PATHS.contains(path.toString())) {
                PATHS.add(path.toString());
                return 1;
            }
        }

        if (visited.size() == n) {
            return 0;
        }

        if (X_MAP.containsKey(point.x)) {
            final List<Point> points = X_MAP.get(point.x);

            for (final Point nextPoint : points) {
                if (visited.contains(nextPoint)) {
                    continue;
                }
                int nextDirection = nextPoint.y > point.y ? 1 : 2;

                if (direction == nextDirection) {
                    continue;
                }

                visited.add(nextPoint);
                path.append(nextPoint);

                count += dfs(nextPoint, visited, path, nextDirection);

                visited.remove(nextPoint);
                path.delete(path.length() - nextPoint.toString().length(), path.length());
            }
        }
        if (Y_MAP.containsKey(point.y)) {
            final List<Point> points = Y_MAP.get(point.y);

            for (final Point nextPoint : points) {
                if (visited.contains(nextPoint)) {
                    continue;
                }
                int nextDirection = nextPoint.x > point.x ? 3 : 4;

                if (direction == nextDirection) {
                    continue;
                }

                visited.add(nextPoint);
                path.append(nextPoint);

                count += dfs(nextPoint, visited, path, nextDirection);

                visited.remove(nextPoint);
                path.delete(path.length() - nextPoint.toString().length(), path.length());
            }
        }
        return count;
    }

    private static class Point {
        private final int x;
        private final int y;

        public Point(final int[] point) {
            this.x = point[0];
            this.y = point[1];
        }

        @Override
        public boolean equals(final Object o) {
            final Point other = (Point) o;
            return this.x == other.x && this.y == other.y;
        }

        @Override
        public String toString() {
            return "[" + this.x + " " + this.y + "]";
        }
    }
}
