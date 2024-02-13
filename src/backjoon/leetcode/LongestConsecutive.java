package backjoon.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {

    public static int longestConsecutive(int[] nums) {
        int numsLength = nums.length;
        if (numsLength == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < numsLength; i++) {
            set.add(nums[i]);
        }

        int max = 1;

        Arrays.sort(nums);
        int index = 0;

        while (!set.isEmpty()) {
            if (index == numsLength) {
                break;
            }
            if (set.contains(nums[index] + 1)) {
                set.remove(nums[index]);
                int count = 1;
                int current = nums[index];
                while (set.contains(++current)) {
                    set.remove(current);
                    count++;
                }
                max = Math.max(max, count);
            }
            index++;
        }

        return max;
    }
}
