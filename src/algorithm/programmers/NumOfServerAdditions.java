package algorithm.programmers;

import java.util.*;

class NumOfServerAdditions {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        Queue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < players.length; i++) {
            int playerNum = players[i];

            while (!pq.isEmpty() && pq.peek() <= i) {
                pq.poll();
            }

            while (pq.size() * m < playerNum - m + 1) {
                pq.add(i + k);
                answer++;
            }
        }

        return answer;
    }
}
