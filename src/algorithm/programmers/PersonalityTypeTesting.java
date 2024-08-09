package algorithm.programmers;

import java.util.HashMap;
import java.util.Map;

public class PersonalityTypeTesting {

    public String solution(String[] survey, int[] choices) {
        final Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < survey.length; i++) {
            final String[] now = survey[i].split("");
            final int choice = choices[i];

            if (choice < 4) {
                final String up = now[0];
                map.put(up, map.getOrDefault(up, 0) + choice * -1 + 4);
                continue;
            }
            if (choice != 4) {
                final String up = now[1];
                map.put(up, map.getOrDefault(up, 0) + choice - 4);
            }
        }

        final StringBuilder sb = new StringBuilder();

        if (map.getOrDefault("R", 0) < map.getOrDefault("T", 0)) {
            sb.append("T");
        } else {
            sb.append("R");
        }
        if (map.getOrDefault("C", 0) < map.getOrDefault("F", 0)) {
            sb.append("F");
        } else {
            sb.append("C");
        }
        if (map.getOrDefault("J", 0) < map.getOrDefault("M", 0)) {
            sb.append("M");
        } else {
            sb.append("J");
        }
        if (map.getOrDefault("A", 0) < map.getOrDefault("N", 0)) {
            sb.append("N");
        } else {
            sb.append("A");
        }

        return sb.toString();
    }
}
