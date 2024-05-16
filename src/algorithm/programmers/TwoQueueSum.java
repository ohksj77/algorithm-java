package algorithm.programmers;

public class TwoQueueSum {

    public static void main(String[] args) {
        System.out.println(
                new TwoQueueSum().solution(new int[] {3, 2, 7, 2}, new int[] {4, 6, 5, 1}));

        System.out.println(new TwoQueueSum().solution(new int[] {1, 1}, new int[] {1, 5}));
    }

    private long sumHalf;

    public int solution(int[] queue1, int[] queue2) {
        long sum1 = sumOfArray(queue1, 0, queue1.length);
        long sum2 = sumOfArray(queue2, 0, queue2.length);

        if (sum1 == sum2) {
            return 0;
        }
        if ((sum1 + sum2) % 2 != 0) {
            return -1;
        }

        sumHalf = (sum1 + sum2) / 2;

        int[] queue = new int[queue1.length + queue2.length];
        int queueLength = queue.length;

        for (int i = 0; i < queue1.length; i++) {
            queue[i] = queue1[i];
        }
        for (int i = 0; i < queue2.length; i++) {
            queue[i + queue1.length] = queue2[i];
        }

        int count = 0;
        int left = 0;
        int right = queue1.length;

        while (count == 0 || left != 0 || right != queue1.length) {
            if (left == right || left >= queueLength && right >= queueLength) {
                return -1;
            }

            if (sum1 == sumHalf) {
                return count;
            }

            if (sum1 < sumHalf) {
                int now = queue[right % queueLength];
                sum1 += now;
                sum2 -= now;
                right += 1;
            } else {
                int now = queue[left % queueLength];
                sum1 -= now;
                sum2 += now;
                left += 1;
            }
            count++;
        }
        return -1;
    }

    private long sumOfArray(int[] array, int start, int end) {
        long result = 0L;
        for (int i = start; i < end; i++) {
            result += array[i];
        }
        return result;
    }
}
