package backjoon.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class KeysAndRooms {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

        List<Integer> firstRoom = rooms.get(0);

        if (firstRoom.isEmpty()) {
            return false;
        }

        boolean[] visited = new boolean[rooms.size()];
        visited[0] = true;

        for (Integer keys : firstRoom) {
            visited[keys] = true;
        }
        Deque<Integer> stack = new ArrayDeque<>(rooms.size());
        stack.addAll(firstRoom);

        while (!stack.isEmpty()) {
            Integer key = stack.pop();
            visited[key] = true;
            for (Integer i : rooms.get(key)) {
                if (!visited[i]) {
                    stack.push(i);
                }
            }
        }

        for (int i = 1; i < rooms.size(); i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
