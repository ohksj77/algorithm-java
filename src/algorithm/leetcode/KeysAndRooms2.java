package algorithm.leetcode;

import java.util.List;

public class KeysAndRooms2 {

    private boolean[] visited;
    private List<List<Integer>> rooms;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        visited = new boolean[rooms.size()];
        this.rooms = rooms;

        dfs(0);

        for (boolean visit : visited) {
            if (!visit) {
                return false;
            }
        }
        return true;
    }

    private void dfs(int index) {
        visited[index] = true;
        for (Integer key : rooms.get(index)) {
            if (!visited[key]) {
                dfs(key);
            }
        }
    }
}
