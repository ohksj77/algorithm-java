package algorithm.programmers;

import java.util.Arrays;

public class CodingTestStudy {

    private static final int INF = 1_000_000;
    private int maxAlp;
    private int maxCop;

    public int solution(int alp, int cop, int[][] problems) {
        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        final int[][] dp = new int[maxAlp + 1][maxCop + 1];

        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        for (final int[] ints : dp) {
            Arrays.fill(ints, INF);
        }

        dp[alp][cop] = 0;

        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                if (i + 1 <= maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                if (j + 1 <= maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
                for (final int[] problem : problems) {
                    if (problem[0] <= i && problem[1] <= j) {
                        final int nextAlp = Math.min(maxAlp, i + problem[2]);
                        final int nextCop = Math.min(maxCop, j + problem[3]);
                        dp[nextAlp][nextCop] =
                                Math.min(dp[nextAlp][nextCop], dp[i][j] + problem[4]);
                    }
                }
            }
        }
        return dp[maxAlp][maxCop];
    }
}
