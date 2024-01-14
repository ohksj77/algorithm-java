package backjoon.leetcode;

import java.util.Arrays;

public class TwoSum2 {

    public int[] twoSum(int[] nums, int target) {
        int[] copy = nums.clone();
        Arrays.sort(copy);

        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            if (target > copy[l] + copy[r]) {
                l += 1;
                continue;
            }
            if (target < copy[l] + copy[r]) {
                r -= 1;
                continue;
            }
            int i = Arrays.binarySearch(nums, copy[l]);
            nums[i] = -1;
            return new int[] {i, Arrays.binarySearch(nums, copy[r])};
        }

        return new int[] {};
    }
}
