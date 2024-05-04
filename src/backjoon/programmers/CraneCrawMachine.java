package backjoon.programmers;

import java.util.*;

public class CraneCrawMachine {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        final List<Queue<Integer>> list = new ArrayList<>();

        for (int i = 0; i < board[0].length; i++) {
            final Queue<Integer> queue = new LinkedList<>();
            list.add(queue);
            for (int j = 0; j < board.length; j++) {
                final int number = board[j][i];
                if (number != 0) {
                    queue.add(number);
                }
            }
        }

        final Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < moves.length; i++) {
            final Queue<Integer> queue = list.get(moves[i] - 1);
            final Integer number = queue.poll();
            if (number != null && number != 0) {
                if (!stack.isEmpty() && number.equals(stack.peek())) {
                    stack.pop();
                    answer += 2;
                    continue;
                }
                stack.push(number);
            }
        }

        return answer;
    }
}
