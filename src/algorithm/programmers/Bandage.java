package algorithm.programmers;

import java.util.*;

public class Bandage {

    public int solution(int[] bandage, int health, int[][] attacks) {

        int maxHealth = health;
        int endTime = attacks[attacks.length - 1][0];

        Map<Integer, Integer> attackTimes = new HashMap<>();
        for (int i = 0; i < attacks.length; i++) {
            attackTimes.put(attacks[i][0], attacks[i][1]);
        }

        int successCount = 0;

        for (int i = 1; i <= endTime; i++) {
            if (attackTimes.containsKey(i)) {
                health -= attackTimes.get(i);

                if (health <= 0) {
                    return -1;
                }
                successCount = 0;
            } else {
                successCount++;
                health += bandage[1];
                if (successCount == bandage[0]) {
                    successCount = 0;
                    health += bandage[2];
                }
                if (health >= maxHealth) {
                    health = maxHealth;
                }
            }
        }
        return health;
    }
}
