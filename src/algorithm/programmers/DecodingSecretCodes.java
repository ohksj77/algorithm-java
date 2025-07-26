package algorithm.programmers;

import java.util.*;

class DecodingSecretCodes {

    private final List<Set<Integer>> all = new ArrayList<>();
    private int n;

    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        makeAll(new HashSet<>(), 1);

        int answer = 0;

        for (int i = 0; i < all.size(); i++) {
            Set<Integer> values = all.get(i);
            boolean flag = true;

            for (int j = 0; j < q.length; j++) {
                int count = 0;

                for (int k = 0; k < 5; k++) {
                    if (values.contains(q[j][k])) {
                        count++;
                    }
                }

                if (ans[j] != count) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer++;
            }
        }
        return answer;
    }

    private void makeAll(Set<Integer> path, int index) {
        if (path.size() == 5) {
            all.add(new HashSet<>(path));
            return;
        }

        for (int i = index; i <= n; i++) {
            path.add(i);
            makeAll(path, i + 1);
            path.remove(i);
        }
    }
}
