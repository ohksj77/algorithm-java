package backjoon.first;

import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon11664 {

    private static Point A;
    private static Point B;
    private static Point C;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final StringTokenizer st = new StringTokenizer(br.readLine());

        A = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        B = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        C = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        Point S = A;
        Point E = B;
        int cnt = 0;

        while(!S.equals(E)) {
            if (++cnt == 100000) break;
            Point mid = new Point((S.x + E.x) / 2, (S.y + E.y) / 2, (S.z + E.z) / 2);

            if (dist(S, C) < dist(E, C)) {
                E = mid;
                continue;
            }
            S = mid;
        }

        bw.write(Double.toString(Math.round(dist(S, C) * 10000000000.0) / 10000000000.0));
        bw.flush();
    }

    private static double dist(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2) + Math.pow(a.z - b.z, 2));
    }

    private static class Point {
        double x, y, z;

        public Point(final double x, final double y, final double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(final Object p) {
            return this.x == ((Point)p).x && this.y == ((Point)p).y && this.z == ((Point)p).z;
        }
    }
}
