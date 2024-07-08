package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법의_숲_탐색 {

    private static final int[][] MOVE_DR = new int[][] {{0, 1}, {-1, 1}, {1, 1}};
    private static final int[][] DR = new int[][] {{0, 1}, {-1, 0}, {1, 0}, {0, -1}};
    private static final int MIN_X = 1;
    private static final int MIN_Y = 0;
    private static int MAX_X;
    private static int MAX_Y;
    private static Element[][] board;
    private static boolean needInitialize;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int R = Integer.parseInt(st.nextToken());
        final int C = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        final int row = R + 3;

        MAX_X = C - 2;
        MAX_Y = R + 1;
        board = new Element[C][row];
        initialize();

        int answer = 0;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            final int c = Integer.parseInt(st.nextToken()) - 1;
            final int d = Integer.parseInt(st.nextToken());

            answer += move(c, d, i);
            if (needInitialize) {
                initialize();
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }

    private static void initialize() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                board[x][y] = new Element();
            }
        }
        needInitialize = false;
    }

    private static int move(final int c, final int d, final int index) {
        final Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {c, 0, d});
        boolean[][] visited = new boolean[board.length][board[0].length];
        visited[c][0] = true;

        while (!q.isEmpty()) {
            final int[] now = q.poll();
            final int cx = now[0];
            final int cy = now[1];
            final int ce = now[2];

            for (int i = 0; i < 3; i++) {
                final int[] dr = MOVE_DR[i];
                final int nx = cx + dr[0];
                final int ny = cy + dr[1];
                int ne = ce;

                if (i == 1) {
                    if (ne == 0) {
                        ne = 3;
                    } else {
                        ne -= 1;
                    }
                } else if (i == 2) {
                    if (ne == 3) {
                        ne = 0;
                    } else {
                        ne += 1;
                    }
                }

                if (MIN_X <= nx && nx <= MAX_X && MIN_Y <= ny && ny <= MAX_Y && !visited[nx][ny]) {
                    visited[nx][ny] = true;

                    if (!isFilled(nx, ny)) {
                        if (ny == MAX_Y) {
                            fill(nx, ny, ne, index);
                            return checkResult(nx, ny);
                        }
                        if (i != 0 && isFilled(nx, cy)) {
                            continue;
                        }
                        q.add(new int[] {nx, ny, ne});
                        break;
                    }
                }
            }
            if (q.isEmpty()) {
                fill(cx, cy, ce, index);
                return checkResult(cx, cy);
            }
        }
        return checkResult(c, 0);
    }

    private static boolean isFilled(final int x, final int y) {
        return board[x][y].isFilled()
                || board[x][y - 1].isFilled()
                || board[x + 1][y].isFilled()
                || board[x][y + 1].isFilled()
                || board[x - 1][y].isFilled();
    }

    private static void fill(final int x, final int y, final int e, final int index) {
        if (y < 4 || y > MAX_Y || x < 1 || x > MAX_X) {
            return;
        }

        if (e == 0) {
            board[x + 1][y].fill(index, Status.FILLED);
            board[x][y + 1].fill(index, Status.FILLED);
            board[x - 1][y].fill(index, Status.FILLED);
            board[x][y - 1].fill(index, Status.EXIT);
            board[x][y].fill(index, Status.FILLED);
        } else if (e == 1) {
            board[x + 1][y].fill(index, Status.EXIT);
            board[x][y + 1].fill(index, Status.FILLED);
            board[x - 1][y].fill(index, Status.FILLED);
            board[x][y - 1].fill(index, Status.FILLED);
            board[x][y].fill(index, Status.FILLED);
        } else if (e == 2) {
            board[x + 1][y].fill(index, Status.FILLED);
            board[x][y + 1].fill(index, Status.EXIT);
            board[x - 1][y].fill(index, Status.FILLED);
            board[x][y - 1].fill(index, Status.FILLED);
            board[x][y].fill(index, Status.FILLED);
        } else {
            board[x + 1][y].fill(index, Status.FILLED);
            board[x][y + 1].fill(index, Status.FILLED);
            board[x - 1][y].fill(index, Status.EXIT);
            board[x][y - 1].fill(index, Status.FILLED);
            board[x][y].fill(index, Status.FILLED);
        }
    }

    private static int checkResult(final int x, final int y) {
        if (y < 4) {
            needInitialize = true;
            return 0;
        }

        final Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, y});
        final boolean[][] visited = new boolean[board.length][board[0].length];
        visited[x][y] = true;
        int max = y - 2;

        while (!q.isEmpty()) {
            final int[] now = q.poll();
            final int cx = now[0];
            final int cy = now[1];

            for (int i = 0; i < 4; i++) {
                final int[] dr = DR[i];
                final int nx = cx + dr[0];
                final int ny = cy + dr[1];

                if (0 <= nx && nx <= MAX_X + 1 && 3 <= ny && ny <= MAX_Y + 1 && !visited[nx][ny]) {
                    final Element next = board[nx][ny];
                    final Element current = board[cx][cy];

                    if (current.index != next.index && current.status != Status.EXIT
                            || !next.isFilled()) {
                        continue;
                    }
                    visited[nx][ny] = true;
                    max = Math.max(max, ny - 2);
                    q.add(new int[] {nx, ny});
                }
            }
        }

        return Math.max(max, 0);
    }

    private static class Element {
        private int index;
        private Status status = Status.UNFILLED;

        public void fill(final int index, final Status status) {
            this.index = index;
            this.status = status;
        }

        public boolean isFilled() {
            return status.isFilled();
        }

        public String toString() {
            return status.name() + " " + index;
        }
    }

    private enum Status {
        UNFILLED,
        FILLED,
        EXIT;

        public boolean isFilled() {
            return this != UNFILLED;
        }
    }
}
