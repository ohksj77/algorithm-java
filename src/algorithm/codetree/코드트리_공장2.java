package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 코드트리_공장2 {

    private static final List<Deque<Item>> belts = new ArrayList<>();
    private static final Map<Integer, Item> itemMap = new HashMap<>();
    private static int m;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= m; i++) {
            belts.add(new ArrayDeque<>());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            final int id = Integer.parseInt(st.nextToken());
            final int value = Integer.parseInt(st.nextToken());
            final int belt = Integer.parseInt(st.nextToken());
            final Item item = new Item(id, value, belt);
            belts.get(belt).addLast(item);
            itemMap.put(id, item);
        }

        final int q = Integer.parseInt(br.readLine());

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 1) {
                final int desiredValue = Integer.parseInt(st.nextToken());
                bw.write(Integer.toString(deliverItem(desiredValue)));
                bw.newLine();
                continue;
            }
            if (command == 2) {
                final int fromBelt = Integer.parseInt(st.nextToken());
                final int toBelt = Integer.parseInt(st.nextToken());
                moveItems(fromBelt, toBelt);
                continue;
            }
            if (command == 3) {
                int cleanBelt = Integer.parseInt(st.nextToken());
                cleanBelt(cleanBelt);
                continue;
            }
            if (command == 4) {
                int itemId = Integer.parseInt(st.nextToken());
                removeItem(itemId);
                continue;
            }
            if (command == 5) {
                int beltNumber = Integer.parseInt(st.nextToken());
                int position = Integer.parseInt(st.nextToken());
                bw.write(Integer.toString(checkItem(beltNumber, position)));
                bw.newLine();
            }
        }

        bw.flush();
    }

    private static int deliverItem(final int value) {
        Item closestItem = null;
        int closestDistance = Integer.MAX_VALUE;
        int closestBelt = Integer.MAX_VALUE;

        for (int b = 1; b <= m; b++) {
            final Deque<Item> items = belts.get(b);
            if (!items.isEmpty()) {
                final Item item = items.peekFirst();
                final int distance = Math.abs(item.value - value);

                if (distance < closestDistance
                        || (distance == closestDistance && b < closestBelt)) {
                    closestDistance = distance;
                    closestBelt = b;
                    closestItem = item;
                }
            }
        }

        if (closestItem == null) {
            return -1;
        }

        belts.get(closestBelt).pollFirst();
        itemMap.remove(closestItem.id);

        return closestItem.value + closestBelt;
    }

    private static void moveItems(final int from, final int to) {
        final Deque<Item> fromBelt = belts.get(from);
        final Deque<Item> toBelt = belts.get(to);

        final int moveCount = (int) Math.ceil(fromBelt.size() / 3.0);
        final List<Item> tempList = new ArrayList<>();
        for (int i = 0; i < moveCount; i++) {
            if (!fromBelt.isEmpty()) {
                tempList.add(fromBelt.pollFirst());
            }
        }
        Collections.reverse(tempList);
        for (final Item item : tempList) {
            toBelt.addFirst(item);
            item.belt = to;
        }
    }

    private static void cleanBelt(final int beltNumber) {
        final Deque<Item> belt = belts.get(beltNumber);
        final List<Item> tempList = new ArrayList<>();

        while (!belt.isEmpty()) {
            final Item item = belt.pollFirst();
            int newBelt = (item.value % m) + 1;
            if (newBelt == beltNumber) {
                newBelt = 1;
            }
            tempList.add(item);
            item.belt = newBelt;
        }
        Collections.reverse(tempList);
        for (final Item item : tempList) {
            belts.get(item.belt).addFirst(item);
        }
    }

    private static void removeItem(final int itemId) {
        final Item item = itemMap.get(itemId);
        if (item != null) {
            belts.get(item.belt).remove(item);
            itemMap.remove(itemId);
        }
    }

    private static int checkItem(final int beltNumber, final int position) {
        final Deque<Item> belt = belts.get(beltNumber);
        if (belt.size() < position) {
            return -1;
        }

        int index = 0;
        for (final Item item : belt) {
            if (++index == position) {
                return item.id;
            }
        }
        return -1;
    }

    private static class Item {
        private final int id;
        private final int value;
        private int belt;

        public Item(final int id, final int value, final int belt) {
            this.id = id;
            this.value = value;
            this.belt = belt;
        }
    }
}
