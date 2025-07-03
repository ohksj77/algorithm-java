package algorithm.programmers;

class PuzzleGameChallenge {
    public int solution(int[] diffs, int[] times, long limit) {
        int max = 300000;
        int min = 1;

        while (min < max) {
            int level = (min + max) / 2;

            if (canResolve(diffs, times, limit, level)) {
                max = level;
            } else {
                min = level + 1;
            }
        }
        return max;
    }

    private boolean canResolve(int[] diffs, int[] times, long limit, int level) {
        long sum = 0;

        for (int i = 0; i < times.length; i++) {
            int time = times[i];
            if (diffs[i] <= level) {
                sum += time;
            } else {
                int minus = diffs[i] - level;
                sum += (time + times[i - 1]) * minus + time;
            }
            if (limit < sum) {
                return false;
            }
        }
        return true;
    }
}
