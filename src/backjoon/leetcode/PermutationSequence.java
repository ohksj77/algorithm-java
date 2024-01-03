package backjoon.leetcode;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {

    public static void main(String[] args) {
        PermutationSequence permutationSequence = new PermutationSequence();

        System.out.println(permutationSequence.getPermutation(4, 9));
    }

    public String getPermutation(int n, int k) {
        List<Integer> factorials = new ArrayList<>(List.of(1));
        List<String> nums = new ArrayList<>(List.of("1"));

        for (int i = 1; i < n; i++) {
            factorials.add(factorials.get(i - 1) * i);
            nums.add(Integer.toString(i + 1));
        }

        k -= 1;
        StringBuilder output = new StringBuilder();

        for (int i = n - 1; i >= 0; i--) {
            Integer now = factorials.get(i);
            int index = k / now;
            k -= index * now;
            output.append(nums.get(index));
            nums.remove(index);
        }

        return output.toString();
    }
}
