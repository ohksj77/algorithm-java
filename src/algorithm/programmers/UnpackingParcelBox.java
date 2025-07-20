package algorithm.programmers;

public class UnpackingParcelBox {

    public int solution(int n, int w, int num) {
        int height = n / w;
        int highest = n % w;

        boolean isFromLeft = (height + 1) % 2 == 1;
        int targetHeight = (num / w) + (num % w != 0 ? 1 : 0);

        if (targetHeight == height && highest == 0 || targetHeight == height + 1 && highest != 0) {
            return 1;
        }

        int answer = height - targetHeight + 1;
        if (highest == 0) {
            return answer;
        }

        int now = (num % w == 0) ? w : (num % w);

        if (targetHeight % 2 == 1) {
            if (isFromLeft) {
                if (highest >= now) {
                    answer += 1;
                }
            } else {
                if (highest >= w - now + 1) {
                    answer += 1;
                }
            }
        } else {
            if (isFromLeft) {
                if (highest >= w - now + 1) {
                    answer += 1;
                }
            } else {
                if (highest >= now) {
                    answer += 1;
                }
            }
        }
        return answer;
    }
}
