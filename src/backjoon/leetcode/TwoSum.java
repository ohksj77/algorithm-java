package backjoon.leetcode;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {

        int arrayLength = nums.length;

        for (int i = 0; i < arrayLength - 1; i++) {
            for (int j = i + 1; j < arrayLength; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }

        return null;
    }
}
