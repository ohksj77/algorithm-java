package algorithm.programmers;

import java.util.*;

public class MovingTheCart {

    private static final int EMPTY = 0;
    private static final int RED_START = 1;
    private static final int BLUE_START = 2;
    private static final int RED_END = 3;
    private static final int BLUE_END = 4;
    private static final int WALL = 5;

    private static final int[][] DR = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private int[][] maze;

    public int solution(int[][] maze) {
        this.maze = maze;

        int[] redStart = null;
        int[] blueStart = null;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == RED_START) {
                    redStart = new int[] {i, j};
                } else if (maze[i][j] == BLUE_START) {
                    blueStart = new int[] {i, j};
                } else if (redStart != null && blueStart != null) {
                    break;
                }
            }
        }
        return bfs(redStart, blueStart);
    }

    private int bfs(int[] red, int[] blue) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {red[0], red[1], blue[0], blue[1], 0});

        Queue<Set<String>> redVisitedQ = new LinkedList<>();
        Queue<Set<String>> blueVisitedQ = new LinkedList<>();

        redVisitedQ.offer(new HashSet<>());
        redVisitedQ.peek().add(Arrays.toString(new int[] {red[0], red[1]}));
        blueVisitedQ.offer(new HashSet<>());
        blueVisitedQ.peek().add(Arrays.toString(new int[] {blue[0], blue[1]}));

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int[] redIndex = new int[] {now[0], now[1]};
            int[] blueIndex = new int[] {now[2], now[3]};
            int count = now[4];

            Set<String> redVisited = redVisitedQ.poll();
            Set<String> blueVisited = blueVisitedQ.poll();

            List<int[]> reds = new ArrayList<>();
            List<int[]> blues = new ArrayList<>();

            if (maze[redIndex[0]][redIndex[1]] == RED_END) {
                reds.add(redIndex);
            } else {
                for (int[] dr : DR) {
                    int x = redIndex[0] + dr[0];
                    int y = redIndex[1] + dr[1];

                    if (0 <= x && x < maze.length && 0 <= y && y < maze[0].length) {
                        if (maze[x][y] != 5
                                && !redVisited.contains(Arrays.toString(new int[] {x, y}))) {
                            reds.add(new int[] {x, y});
                        }
                    }
                }
            }

            if (maze[blueIndex[0]][blueIndex[1]] == BLUE_END) {
                blues.add(blueIndex);
            } else {
                for (int[] dr : DR) {
                    int x = blueIndex[0] + dr[0];
                    int y = blueIndex[1] + dr[1];

                    if (0 <= x && x < maze.length && 0 <= y && y < maze[0].length) {
                        if (maze[x][y] != 5
                                && !blueVisited.contains(Arrays.toString(new int[] {x, y}))) {
                            blues.add(new int[] {x, y});
                        }
                    }
                }
            }

            for (int[] nowRed : reds) {
                for (int[] nowBlue : blues) {
                    if (nowRed[0] == nowBlue[0] && nowRed[1] == nowBlue[1]) {
                        continue;
                    }
                    if (nowRed[0] == blueIndex[0]
                            && nowRed[1] == blueIndex[1]
                            && nowBlue[0] == redIndex[0]
                            && nowBlue[1] == redIndex[1]) {
                        continue;
                    }
                    if (maze[nowRed[0]][nowRed[1]] == RED_END
                            && maze[nowBlue[0]][nowBlue[1]] == BLUE_END) {
                        return count + 1;
                    }
                    q.offer(new int[] {nowRed[0], nowRed[1], nowBlue[0], nowBlue[1], count + 1});
                    Set<String> rv = new HashSet<>(redVisited);
                    Set<String> bv = new HashSet<>(blueVisited);
                    rv.add(Arrays.toString(new int[] {nowRed[0], nowRed[1]}));
                    bv.add(Arrays.toString(new int[] {nowBlue[0], nowBlue[1]}));
                    redVisitedQ.offer(rv);
                    blueVisitedQ.offer(bv);
                }
            }
        }
        return 0;
    }
}
