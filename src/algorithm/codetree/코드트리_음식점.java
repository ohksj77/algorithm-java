package algorithm.codetree;

import java.io.*;
import java.util.*;

public class 코드트리_음식점 {

    private static int k;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] input = br.readLine().split(" ");
        final int n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        final Queue<Menu> pq = new PriorityQueue<>();
        final Map<Integer, Menu> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            final int value = Integer.parseInt(br.readLine().trim());

            if (map.containsKey(value)) {
                map.get(value).increase();
                pq.add(pq.poll());
            } else {
                final Menu menu = new Menu(value);
                pq.add(menu);
                map.put(value, menu);
            }

            bw.write(pq.peek().toString());
            bw.newLine();
        }
        bw.flush();
    }

    private static class Menu implements Comparable<Menu> {
        private final int index;
        private int count = 1;

        public Menu(final int index) {
            this.index = index;
        }

        public void increase() {
            this.count++;
        }

        public boolean canRecommend() {
            return this.count >= k;
        }

        @Override
        public int compareTo(final Menu other) {
            final boolean thisResult = this.canRecommend();
            final boolean otherResult = other.canRecommend();

            if (thisResult && otherResult) {
                return this.index - other.index;
            }
            if (thisResult) {
                return -1;
            }
            if (otherResult) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            if (this.canRecommend()) {
                return Integer.toString(index);
            }
            return "-1";
        }
    }
}
