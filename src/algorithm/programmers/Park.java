package algorithm.programmers;

import java.util.*;

class Park {

    private static final int[][] DR = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private String[][] park;

    public int solution(int[] mats, String[][] park) {
        this.park = park;
        Queue<Integer> x = new LinkedList<>();
        Queue<Integer> y = new LinkedList<>();

        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[0].length; j++) {
                String value = park[i][j];
                if (value.equals("-1")) {
                    x.offer(i);
                    y.offer(j);
                }
            }
        }

        int length = -1;

        while (!x.isEmpty() && !y.isEmpty()) {
            int cx = x.poll();
            int cy = y.poll();

            int size = find(cx, cy, 1);
            length = Math.max(length, size);
        }

        Arrays.sort(mats);
        for (int i = mats.length - 1; i >= 0; i--) {
            int mat = mats[i];

            if (mat <= length) {
                return mat;
            }
        }
        return -1;
    }

    private int find(int x, int y, int size) {

        boolean flag = true;

        while (flag && x + size < park.length && y + size < park[0].length) {
            for (int i = x; i < x + size + 1 && i < park.length; i++) {
                for (int j = y; j < y + size + 1 && j < park[0].length; j++) {
                    if (!park[i][j].equals("-1")) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }
            if (flag) {
                size++;
            }
        }
        return size;
    }
}
