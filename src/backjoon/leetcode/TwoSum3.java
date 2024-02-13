package backjoon.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum3 {

    public int[] twoSum(int[] nums, int target) {

        int arrayLength = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arrayLength - 1; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < arrayLength; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                final Integer other = map.get(complement);
                if (i == other) {
                    continue;
                }
                return new int[] {i, other};
            }
        }

        return null;
    }
}
