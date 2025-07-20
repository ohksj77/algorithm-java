package algorithm.programmers;

import java.util.*;

class SealedSpells {
    public String solution(long n, String[] bans) {
        Queue<String> bansQueue = new LinkedList<>();

        Arrays.sort(
                bans,
                (o1, o2) -> {
                    if (o1.length() == o2.length()) {
                        return o1.compareTo(o2);
                    }
                    return o1.length() - o2.length();
                });

        for (int i = 0; i < bans.length; i++) {
            bansQueue.add(bans[i]);
        }

        while (!bansQueue.isEmpty()) {
            String cur = bansQueue.peek();
            String target = getString(n);
            if (cur.length() < target.length()
                    || (cur.length() == target.length() && cur.compareTo(target) <= 0)) {
                bansQueue.poll();
                n++;
            } else {
                break;
            }
        }
        return getString(n);
    }

    private String getString(long n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            long remained = n % 26;
            n /= 26;
            if (remained == 0) {
                n--;
                sb.append('z');
            } else {
                sb.append((char) ('a' + remained - 1));
            }
        }
        return sb.reverse().toString();
    }
}
