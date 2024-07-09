package algorithm.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheMostReceivedGifts {
    public int solution(String[] friends, String[] gifts) {
        final Map<String, List<String>> giveMap = new HashMap<>();
        final Map<String, Integer> pointMap = new HashMap<>();
        final Map<String, Integer> resultMap = new HashMap<>();

        for (final String gift : gifts) {
            final String[] array = gift.split(" ");
            final String give = array[0];
            final String recieve = array[1];

            final List<String> giftList = giveMap.getOrDefault(recieve, new ArrayList<>());
            giftList.add(give);
            giveMap.put(recieve, giftList);

            pointMap.put(give, pointMap.getOrDefault(give, 0) + 1);
            pointMap.put(recieve, pointMap.getOrDefault(recieve, 0) - 1);
        }

        final int length = friends.length;

        for (int i = 0; i < length - 1; i++) {
            final String left = friends[i];

            if (!giveMap.containsKey(left)) {
                continue;
            }
            final List<String> leftList = giveMap.get(left);

            for (int j = i + 1; j < length; j++) {
                final String right = friends[j];

                final List<String> rightList = giveMap.getOrDefault(right, new ArrayList<>());

                final int leftCount = Collections.frequency(leftList, right);
                final int rightCount = Collections.frequency(rightList, left);

                if (leftCount > rightCount) {
                    resultMap.put(right, resultMap.getOrDefault(right, 0) + 1);
                    continue;
                }
                if (leftCount < rightCount) {
                    resultMap.put(left, resultMap.getOrDefault(left, 0) + 1);
                    continue;
                }

                final int leftPoint = pointMap.getOrDefault(left, 0);
                final int rightPoint = pointMap.getOrDefault(right, 0);

                if (leftPoint < rightPoint) {
                    resultMap.put(right, resultMap.getOrDefault(right, 0) + 1);
                    continue;
                }
                if (leftPoint > rightPoint) {
                    resultMap.put(left, resultMap.getOrDefault(left, 0) + 1);
                }
            }
        }

        int answer = 0;

        for (final int giftCount : resultMap.values()) {
            if (answer < giftCount) {
                answer = giftCount;
            }
        }

        return answer;
    }
}
