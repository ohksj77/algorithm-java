package backjoon.study.fifth;

import java.util.Arrays;

public class MuziMukbang {

    public static void main(String[] args) {
        int answer = solution(new int[]{3, 1, 2}, 5);
        System.out.println(answer);
    }
    public static int solution(int[] food_times, long k) {
        int count = 0;
        int sum = Arrays.stream(food_times).sum();
        while (count <= sum) {
            for (int i = 0; i < food_times.length; i++) {
                if (count == k) {
                    return i + 1;
                }
                if (food_times[i] != 0) {
                    food_times[i]--;
                } else {
                    continue;
                }
                count++;
            }
        }
        return -1;
    }
}
