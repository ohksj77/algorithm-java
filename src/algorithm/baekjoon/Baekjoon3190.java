package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon3190 {

    private static boolean[][] apple;
    private static boolean[][] body;
    private static final HashMap<Integer, Character> SECOND = new HashMap<>();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int n = Integer.parseInt(br.readLine());
        final int m = Integer.parseInt(br.readLine());
        apple = new boolean[n][n];
        body = new boolean[n][n];

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            final int n1 = Integer.parseInt(st.nextToken());
            final int n2 = Integer.parseInt(st.nextToken());
            apple[n1 - 1][n2 - 1] = true;
        }

        final int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            final int num = Integer.parseInt(st.nextToken());
            final char move = st.nextToken().charAt(0);
            SECOND.put(num + 1, move);
        }

        bw.write(Integer.toString(snake()));
        bw.flush();
    }

    public static int snake() {

        final Queue<Moving> queue = new LinkedList<>();
        final Queue<Integer[]> removeList = new LinkedList<>();
        removeList.add(new Integer[] {0, 0});

        body[0][0] = true;
        queue.add(new Moving(0, 0, 0, 1));

        while (!queue.isEmpty()) {
            final Moving now = queue.poll();
            int nowY = now.y;
            int nowX = now.x;
            int dir = now.direction;
            final int count = now.count;

            if (SECOND.containsKey(count)) {
                char nextDir = SECOND.get(count);
                if (dir == 0) {
                    dir = nextDir == 'L' ? 2 : 3;
                } else if (dir == 1) {
                    dir = nextDir == 'L' ? 3 : 2;
                } else if (dir == 2) {
                    dir = nextDir == 'L' ? 1 : 0;
                } else if (dir == 3) {
                    dir = nextDir == 'L' ? 0 : 1;
                }
            }

            if (dir == 0) {
                nowX++;
            } else if (dir == 1) {
                nowX--;
            } else if (dir == 2) {
                nowY--;
            } else if (dir == 3) {
                nowY++;
            }

            if (nowX < 0 || nowY < 0 || nowX >= apple.length || nowY >= apple.length) {
                return count;
            }

            if (body[nowY][nowX]) {
                return count;
            }

            if (apple[nowY][nowX]) {
                apple[nowY][nowX] = false;
            } else {
                final Integer[] last = removeList.poll();
                body[last[0]][last[1]] = false;
            }

            removeList.add(new Integer[] {nowY, nowX});
            body[nowY][nowX] = true;

            queue.add(new Moving(nowY, nowX, dir, count + 1));
        }

        return -1;
    }

    private static class Moving {
        private final int y;
        private final int x;
        private final int direction;
        private final int count;

        Moving(int y, int x, int direction, int count) {
            this.y = y;
            this.x = x;
            this.direction = direction;
            this.count = count;
        }
    }
}
