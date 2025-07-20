package algorithm.programmers;

import java.time.*;
import java.util.*;

class FlexibleWorkArrangements {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        Map<Integer, LocalTime> goalTimes = new HashMap<>();

        for (int i = 0; i < schedules.length; i++) {
            int schedule = schedules[i];
            goalTimes.put(i, LocalTime.of(schedule / 100, schedule % 100, 0).plusMinutes(10));
        }

        boolean[] flags = new boolean[schedules.length];

        for (int i = 0; i < timelogs.length; i++) {
            int[] timelog = timelogs[i];

            for (int day = 0; day < 7; day++) {
                if ((day + startday) % 7 == 0 || (day + startday) % 7 == 6) {
                    continue;
                }

                if (flags[i]) {
                    continue;
                }

                int value = timelog[day];
                LocalTime time = LocalTime.of(value / 100, value % 100, 0);
                LocalTime goal = goalTimes.get(i);

                if (!goal.isAfter(time) && !goal.equals(time)) {
                    flags[i] = true;
                }
            }
        }
        int answer = 0;

        for (boolean flag : flags) {
            if (!flag) {
                answer++;
            }
        }
        return answer;
    }
}
