class Solution {
    public int solution(int n, int w, int num) {
        int answer = ((n - num) / (2 * w)) * 2 + 1;

        if (((num - 1) % (2 * w) / w) == ((n - 1) % (2 * w) / w)) {
            if (((num - 1) % (2 * w)) > ((n - 1) % (2 * w))) {
                answer++;
            }
        }
        else {
            if (((num - 1) % (2 * w) + (n - 1) % (2 * w)) >= (2 * w - 1)) {
                answer++;
            }
        }
        return answer;
    }
}
