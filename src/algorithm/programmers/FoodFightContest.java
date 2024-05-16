package algorithm.programmers;

public class FoodFightContest {

    public String solution(int[] food) {
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < food.length; i++) {
            final int f = food[i];
            sb.append(String.valueOf(i).repeat((int) (f / 2)));
        }

        final String left = sb.toString();
        final String right = sb.reverse().toString();

        return left + "0".repeat(food[0]) + right;
    }
}
