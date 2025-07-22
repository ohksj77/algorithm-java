package algorithm.programmers;

import java.util.*;

class OddEvenTree {

    private final Set<Integer> set = new HashSet<>();
    private Map<Integer, List<Integer>> graph = new HashMap<>();
    private int forward = 0;
    private int reverse = 0;

    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[] {0, 0};

        for (int node : nodes) {
            graph.put(node, new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        for (int node : nodes) {
            if (set.contains(node)) {
                continue;
            }

            forward = 0;
            reverse = 0;
            bfs(node);

            if (forward == 1) {
                answer[1]++;
            }
            if (reverse == 1) {
                answer[0]++;
            }
        }
        return answer;
    }

    private void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();

        set.add(start);
        q.add(start);

        while (!q.isEmpty()) {
            int now = q.poll();

            int nowCnt = graph.get(now).size() - 1;
            if (now % 2 == 0) {
                if (nowCnt % 2 == 0) {
                    forward++;
                } else {
                    reverse++;
                }
            } else {
                if (nowCnt % 2 == 1) {
                    forward++;
                } else {
                    reverse++;
                }
            }

            List<Integer> childs = graph.get(now);

            for (int child : childs) {
                if (set.contains(child)) {
                    continue;
                }
                set.add(child);
                q.add(child);
            }
        }
    }
}
