package algorithm.programmers;

import java.util.*;

class MenuRenewal {
    public String[] solution(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();
        Set<String> courseMenus = new HashSet<>();
        for (int amount : course) {
            courseMenus.clear();
            for (String order : orders) {
                char[] menus = order.toCharArray();
                Arrays.sort(menus);
                boolean[] picks = new boolean[menus.length];
                combineMenus(menus, picks, 0, courseMenus, amount);
            }

            int maxCount = 2;
            List<String> buffer = new ArrayList<>();
            for (String courseMenu : courseMenus) {
                int orderCount = getOrderCount(orders, courseMenu);
                if (orderCount > maxCount) {
                    maxCount = orderCount;
                    buffer.clear();
                }
                if (orderCount >= maxCount) {
                    buffer.add(courseMenu);
                }
            }

            result.addAll(buffer);
        }

        Collections.sort(result);
        return result.toArray(String[]::new);
    }

    private void combineMenus(
            char[] menus, boolean[] picks, int index, Set<String> result, int count) {
        if (count == 0) {
            result.add(fromPicksToString(menus, picks));
            return;
        }

        for (int i = index; i < menus.length; i++) {
            picks[i] = true;
            combineMenus(menus, picks, i + 1, result, count - 1);
            picks[i] = false;
        }
    }

    private String fromPicksToString(char[] menus, boolean[] picks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < picks.length; i++) {
            if (picks[i]) {
                sb.append(menus[i]);
            }
        }

        return sb.toString();
    }

    private int getOrderCount(String[] orders, String course) {
        int match = 0;
        for (String order : orders) {
            if (course.chars().map(order::indexOf).allMatch(c -> c >= 0)) {
                match += 1;
            }
        }
        return match;
    }
}
