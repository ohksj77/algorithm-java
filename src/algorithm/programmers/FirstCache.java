package algorithm.programmers;

import java.util.*;

class FirstCache {

    public static void main(String[] args) {
        System.out.println(
                new FirstCache()
                        .solution(
                                3,
                                new String[] {
                                    "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo",
                                    "Seoul", "NewYork", "LA"
                                }));
    }

    public int solution(int cacheSize, String[] cities) {

        return lru(cacheSize, cities);
    }

    private int lru(int cacheSize, String[] cities) {

        if (cacheSize == 0) {
            return cities.length * 5;
        }

        Queue<String> cache = new LinkedList<>();

        int answer = 0;

        for (int i = 0; i < cities.length; i++) {
            String now = cities[i].toLowerCase();
            if (cache.contains(now)) {
                answer += 1;
                cache.remove(now);
                cache.add(now);
            } else {
                answer += 5;
                if (cache.size() == cacheSize) {
                    cache.poll();
                }
                cache.add(now);
            }
        }

        return answer;
    }
}
