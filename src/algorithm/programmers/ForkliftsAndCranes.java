package algorithm.programmers;

import java.util.*;

public class ForkliftsAndCranes {

    private List<int[]> clears = new ArrayList<>();
    private static final int[][] DR = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private String[] storage;
    private int answer;

    public int solution(String[] storage, String[] requests) {
        this.storage = storage;
        this.answer = storage.length * storage[0].length();

        Map<Character, List<int[]>> map = new HashMap<>();

        for (int i = 0; i < storage.length; i++) {
            for (int j = 0; j < storage[0].length(); j++) {
                char value = storage[i].charAt(j);
                List<int[]> list = map.getOrDefault(value, new ArrayList<>());
                list.add(new int[] {i, j});
                map.put(value, list);
            }
        }

        for (String request : requests) {
            char value = request.charAt(0);

            List<int[]> list = map.get(value);
            if (list == null) {
                continue;
            }

            if (request.length() == 1) {
                for (int[] array : list) {
                    if (storage[array[0]].charAt(array[1]) == '.') {
                        continue;
                    }
                    bfs(array[0], array[1], value);
                }

            } else if (request.length() == 2) {
                for (int[] array : list) {
                    if (storage[array[0]].charAt(array[1]) == '.') {
                        continue;
                    }
                    clears.add(array);
                }
            }
            for (int[] clear : clears) {
                setDefaultChar(clear[0], clear[1]);
            }
            clears = new ArrayList<>();
        }

        return answer;
    }

    private void bfs(int x, int y, char value) {
        if (x == 0 || x == storage.length - 1 || y == 0 || y == storage[0].length() - 1) {
            char c = storage[x].charAt(y);
            clears.add(new int[] {x, y});
            return;
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, y});
        boolean[][] visited = new boolean[storage.length][storage[0].length()];

        while (!q.isEmpty()) {
            int[] now = q.poll();
            visited[now[0]][now[1]] = true;

            for (int[] dr : DR) {
                int nx = now[0] + dr[0];
                int ny = now[1] + dr[1];

                if (!(0 <= nx && nx < storage.length && 0 <= ny && ny < storage[0].length())) {
                    continue;
                }

                if (nx == 0
                        || nx == storage.length - 1
                        || ny == 0
                        || ny == storage[0].length() - 1) {
                    char c = storage[nx].charAt(ny);
                    if (c == '.') {
                        clears.add(new int[] {x, y});
                        return;
                    }
                }

                if (!visited[nx][ny] && storage[nx].charAt(ny) == '.') {
                    q.offer(new int[] {nx, ny});
                }
            }
        }
    }

    private void setDefaultChar(int x, int y) {
        String string = storage[x];
        if (string.length() <= y || y < 0) {
            return;
        }

        if (string.charAt(y) == '.') {
            return;
        }

        StringBuilder sb = new StringBuilder(string);
        sb.setCharAt(y, '.');
        answer--;
        storage[x] = sb.toString();
    }
}
