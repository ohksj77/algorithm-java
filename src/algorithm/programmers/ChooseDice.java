package algorithm.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChooseDice {
    private static final List<Integer> choice = new ArrayList<>();
    private static int[][] dices;
    private static int N;
    private static List<Integer> arrA;
    private static List<Integer> arrB;
    private static int[] answer;
    private static int max = Integer.MIN_VALUE;

    public static int[] solution(int[][] dice) {
        N = dice.length;
        dices = dice;
        answer = new int[N / 2];
        choiceDice(0, 0);
        return answer;
    }

    public static void makeArr(
            final int depth, final int[][] dice, final int sum, final List<Integer> arr) {
        if (depth == N / 2) {
            arr.add(sum);
            return;
        }
        for (int i = 0; i < 6; i++) {
            final int newSum = sum + dice[depth][i];
            makeArr(depth + 1, dice, newSum, arr);
        }
    }

    public static void makeArrAB() {
        arrA = new ArrayList<>();
        arrB = new ArrayList<>();

        final int[][] diceA = new int[N / 2][6];
        final int[][] diceB = new int[N / 2][6];
        int a = 0;
        int b = 0;
        for (int i = 0; i < N; i++) {
            if (choice.contains(i)) {
                diceA[a] = dices[i];
                a++;
                continue;
            }
            diceB[b] = dices[i];
            b++;
        }

        makeArr(0, diceA, 0, arrA);
        makeArr(0, diceB, 0, arrB);
    }

    public static int calculateWinningPercent() {
        int count = 0;

        makeArrAB();
        Collections.sort(arrB);

        for (int i = 0; i < arrA.size(); i++) {
            final int number = arrA.get(i);

            int left = 0;
            int right = arrB.size() - 1;

            int index = Integer.MIN_VALUE;
            while (left <= right) {
                final int middle = (left + right) / 2;

                if (arrB.get(middle) < number) {
                    left = middle + 1;
                    index = Math.max(index, middle);
                    continue;
                }
                right = middle - 1;
            }
            if (index != Integer.MIN_VALUE) {
                count += index + 1;
            }
        }
        return count;
    }

    public static void choiceDice(final int depth, final int s) {
        if (depth == N / 2) {
            final int winning = calculateWinningPercent();
            if (max < winning) {
                max = winning;
                for (int i = 0; i < choice.size(); i++) {
                    answer[i] = choice.get(i) + 1;
                }
            }
            return;
        }
        for (int i = s; i < N; i++) {
            choice.add(i);
            choiceDice(depth + 1, i + 1);
            choice.remove(choice.size() - 1);
        }
    }
}
