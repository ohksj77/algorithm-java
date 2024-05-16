package algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {

        int[] result = new int[temperatures.length];
        Deque<int[]> stack = new ArrayDeque<>(temperatures.length);

        for (int day = 0; day < temperatures.length; day++) {
            final int temperature = temperatures[day];
            while (!stack.isEmpty() && temperatures[stack.peek()[1]] < temperature) {
                int index = stack.pop()[0];
                result[index] = day - index;
            }
            stack.push(new int[] {day, temperature});
        }

        return result;
    }
}
