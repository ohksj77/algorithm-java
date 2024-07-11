package algorithm.programmers;

import java.util.HashMap;
import java.util.Map;

public class DonutAndBarGraph {
    public int[] solution(int[][] edges) {
        final Map<Integer, int[]> nodeCnt = new HashMap<>();
        final int[] answer = {0, 0, 0, 0};

        for (final int[] edge : edges) {
            final int a = edge[0];
            final int b = edge[1];
            if (!nodeCnt.containsKey(a)) {
                nodeCnt.put(a, new int[] {0, 0});
            }
            if (!nodeCnt.containsKey(b)) {
                nodeCnt.put(b, new int[] {0, 0});
            }
            nodeCnt.get(a)[0] += 1;
            nodeCnt.get(b)[1] += 1;
        }

        for (final Map.Entry<Integer, int[]> entry : nodeCnt.entrySet()) {
            int[] counts = entry.getValue();

            if (counts[0] >= 2 && counts[1] == 0) {
                answer[0] = entry.getKey();
            } else if (counts[0] == 0 && counts[1] > 0) {
                answer[2]++;
            } else if (counts[0] >= 2 && counts[1] >= 2) {
                answer[3]++;
            }
        }

        answer[1] = nodeCnt.get(answer[0])[0] - answer[2] - answer[3];

        return answer;
    }
}
