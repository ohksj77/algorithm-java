package algorithm.programmers;

import java.util.*;

public class OilDrilling {

    private static final int[][] DR = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final Map<Integer, Set<Integer>> xMap = new HashMap<>();
    private static final Map<Integer, Integer> sizeMap = new HashMap<>();
    private int[][] land;

    public int solution(int[][] land) {
        this.land = land;

        int index = 0;

        for (int x = 0; x < land[0].length; x++) {
            for (int y = 0; y < land.length; y++) {
                if (land[y][x] != 1) {
                    continue;
                }
                findSize(x, y, index++);
            }
        }
        int answer = 0;

        for (Set<Integer> set : xMap.values()) {
            int sum = 0;

            for (int i : set) {
                sum += sizeMap.getOrDefault(i, 0);
            }

            if (answer < sum) {
                answer = sum;
            }
        }
        return answer;
    }

    private void findSize(int x, int y, int index) {
        land[y][x] = 2;

        Set<Integer> set = xMap.getOrDefault(x, new HashSet<>());
        set.add(index);
        xMap.put(x, set);

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, y});

        int size = 1;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int cx = now[0];
            int cy = now[1];

            for (int[] dr : DR) {
                int nx = cx + dr[0];
                int ny = cy + dr[1];

                if (0 <= nx && nx < land[0].length && 0 <= ny && ny < land.length) {
                    if (land[ny][nx] == 1) {
                        land[ny][nx] = 2;
                        q.offer(new int[] {nx, ny});
                        size++;

                        Set<Integer> nowSet = xMap.getOrDefault(nx, new HashSet<>());
                        nowSet.add(index);
                        xMap.put(nx, nowSet);
                    }
                }
            }
        }
        sizeMap.put(index, size);
    }
}
