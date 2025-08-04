package algorithm.programmers;

import java.util.*;

public class FindingRiskOfCollision {

    private Queue<int[]>[] paths;
    private int size;

    public int solution(int[][] points, int[][] routes) {
        size = routes.length;
        paths = new Queue[size];
        for (int i = 0; i < size; i++) {
            paths[i] = new LinkedList<>();
        }
        move(points, routes);
        return searchCollision();
    }

    private int searchCollision() {
        int count = 0;
        int answer = 0;

        while (count != size) {
            int[][] map = new int[101][101];
            count = 0;

            for (int i = 0; i < size; i++) {
                if (paths[i].isEmpty()) {
                    count++;
                    continue;
                }
                int[] temp = paths[i].poll();
                map[temp[0]][temp[1]]++;
            }
            for (int i = 0; i < 101; i++) {
                for (int j = 0; j < 101; j++) {
                    if (map[i][j] > 1) {
                        answer++;
                    }
                }
            }
        }
        return answer;
    }

    private void move(int[][] points, int[][] routes) {
        for (int i = 0; i < size; i++) {
            int[] point = points[routes[i][0] - 1];
            int x = point[0];
            int y = point[1];

            paths[i].add(new int[] {x, y});

            for (int j = 1; j < routes[0].length; j++) {
                int[] next_point = points[routes[i][j] - 1];
                int nx = next_point[0];
                int ny = next_point[1];

                int xp = nx - x;
                int yp = ny - y;
                while (xp != 0) {
                    if (xp > 0) {
                        xp--;
                        x++;
                        paths[i].add(new int[] {x, y});
                    } else {
                        xp++;
                        x--;
                        paths[i].add(new int[] {x, y});
                    }
                }
                while (yp != 0) {
                    if (yp > 0) {
                        yp--;
                        y++;
                        paths[i].add(new int[] {x, y});
                    } else {
                        yp++;
                        y--;
                        paths[i].add(new int[] {x, y});
                    }
                }
            }
        }
    }
}
