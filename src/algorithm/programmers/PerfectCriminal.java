class Solution {
    public int solution(int[][] info, int n, int m) {
        boolean[][] dp = new boolean[n][m];
        dp[0][0] = true;

        for (int[] item : info) {
            boolean[][] next = new boolean[n][m];

            for (int a = 0; a < n; a++) {
                for (int b = 0; b < m; b++) {
                    if (!dp[a][b]) {
                        continue;
                    }

                    if (a + item[0] < n) {
                        next[a + item[0]][b] = true;
                    }
                    
                    if (b + item[1] < m) {
                        next[a][b + item[1]] = true;
                    }
                }
            }
            dp = next;
        }

        int answer = Integer.MAX_VALUE;
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < m; b++) {
                if (dp[a][b]) {
                    answer = Math.min(answer, a);
                }
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
