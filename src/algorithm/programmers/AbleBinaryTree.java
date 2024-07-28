package algorithm.programmers;

public class AbleBinaryTree {

    private int possible;

    public int[] solution(long[] numbers) {
        final int[] answer = new int[numbers.length];

        for (int k = 0; k < answer.length; k++) {
            final String binaryNum = Long.toBinaryString(numbers[k]);

            int fullTreeLen = 0;
            int h = 1;

            while (fullTreeLen < binaryNum.length()) {
                fullTreeLen = (int) Math.pow(2, h++) - 1;
            }

            final boolean[] isOne = new boolean[fullTreeLen];

            int notDummyIdx = isOne.length - binaryNum.length();
            for (int i = 0; i < binaryNum.length(); i++) {
                isOne[notDummyIdx++] = binaryNum.charAt(i) == '1';
            }

            possible = 1;
            dfs(0, isOne.length - 1, false, isOne);
            answer[k] = possible;
        }
        return answer;
    }

    private void dfs(
            final int start, final int end, final boolean isParentZero, final boolean[] isOne) {
        if (possible == 0) {
            return;
        }

        final int mid = (start + end) / 2;

        if (isParentZero && isOne[mid]) {
            possible = 0;
            return;
        }

        if (start != end) {
            dfs(start, mid - 1, !isOne[mid], isOne);
            dfs(mid + 1, end, !isOne[mid], isOne);
        }
    }
}
