package algorithm.programmers;

public class MazeEscape {

    private static final int[][] DR = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    private static final String[] COMMAND = new String[] {"d", "l", "r", "u"}; // 사전순
    private static final String IMPOSSIBLE = "impossible";
    private boolean isAnswerFound = false;
    private String answer = "";
    private int N;
    private int M;
    private int R;
    private int C;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        R = r;
        C = c;

        if (x == r && y == c) {
            return answer;
        }
        if (cannotReach(x, y, k)) {
            return IMPOSSIBLE;
        }

        dfs(x, y, k, new StringBuilder());

        if (!isAnswerFound) {
            return IMPOSSIBLE;
        }
        return answer;
    }

    private void dfs(int x, int y, int count, StringBuilder command) {
        if (cannotReach(x, y, count) || isAnswerFound) {
            return;
        }

        if (R == x && C == y && count == 0) {
            isAnswerFound = true;
            answer = command.toString();
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[] dr = DR[i];
            int nx = x + dr[0];
            int ny = y + dr[1];
            if (nx < 1 || nx > N || ny < 1 || ny > M) {
                continue;
            }
            dfs(nx, ny, count - 1, command.append(COMMAND[i]));
            command.deleteCharAt(command.length() - 1);
        }
    }

    /** count(k에서 움직인 횟수를 뺀 변수)가 distance(움직여야할 거리)보다 작거나 count - distance가 홀수면 도달 불가능 */
    private boolean cannotReach(int x, int y, int count) {
        int distance = distanceToDest(x, y);
        return count < distance || (count - distance) % 2 != 0;
    }

    /** (x, y)에서 (R, C)까지의 거리 */
    private int distanceToDest(int x, int y) {
        return Math.abs(x - R) + Math.abs(y - C);
    }
}
