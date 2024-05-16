package algorithm.programmers;

public class FailureRate {

    public int[] solution(int N, int[] stages) {
        final int[] answer = new int[N];
        final double[] failureRates = new double[N];
        final int arrLength = stages.length;
        int index = arrLength;

        for (final int stage : stages) {
            if (stage != N + 1) answer[stage - 1] += 1;
        }

        for (int i = 0; i < N; i++) {
            final int personNum = answer[i];
            failureRates[i] = (double) personNum / index;
            index -= personNum;
            answer[i] = i + 1;
        }

        double swapRates = 0;
        int swapAnswer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N - i; j++) {
                if (failureRates[j - 1] < failureRates[j]) {
                    swapRates = failureRates[j - 1];
                    failureRates[j - 1] = failureRates[j];
                    failureRates[j] = swapRates;

                    swapAnswer = answer[j - 1];
                    answer[j - 1] = answer[j];
                    answer[j] = swapAnswer;
                }
            }
        }
        return answer;
    }
}
