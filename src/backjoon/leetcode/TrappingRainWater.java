package backjoon.leetcode;

public class TrappingRainWater {

    public int trap(int[] height) {

        if (height.length <= 1) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;

        while (height[left] == 0) {
            left += 1;
        }

        while (height[right] == 0) {
            right -= 1;
        }

        if (left >= right) {
            return 0;
        }

        int answer = 0;

        while (left + 1 != right) {
            int leftHeight = height[left];
            int rightHeight = height[right];

            if (leftHeight < rightHeight) {
                left += 1;
                if (height[left] < leftHeight) {
                    answer += leftHeight - height[left];
                    height[left] = leftHeight;
                }
            } else {
                right -= 1;
                if (height[right] < rightHeight) {
                    answer += rightHeight - height[right];
                    height[right] = rightHeight;
                }
            }
        }

        return answer;
    }
}
