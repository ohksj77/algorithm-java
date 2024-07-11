package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class 거스름돈_계산하기 {

    private static final int INF = 2001;
    private static final int IMPOSSIBLE = -1;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] ns = br.readLine().split(" ");
        final int n = Integer.parseInt(ns[0]);
        final int s = Integer.parseInt(ns[1]);

        final int[] change = new int[s + 1];
        Arrays.fill(change, 1, s + 1, INF);

        final Queue<Coin> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            final String[] va = br.readLine().split(" ");
            int v = Integer.parseInt(va[0]);
            int a = Integer.parseInt(va[1]);
            pq.add(new Coin(v, a));
        }

        for (final Coin coin : pq) {
            final int v = coin.v;

            for (int i = 0; i <= s; i++) {
                for (int j = 1; j <= v; j++) {
                    final int sum = i + v * j;
                    if (sum > s) {
                        break;
                    }
                    change[sum] = Math.min(change[sum], change[i] + j);
                }
            }
        }

        int result = change[s];

        if (result == INF) {
            result = IMPOSSIBLE;
        }
        bw.write(Integer.toString(result));
        bw.flush();
    }

    private static class Coin implements Comparable<Coin> {
        private final int v;
        private final int a;

        public Coin(final int v, final int a) {
            this.v = v;
            this.a = a;
        }

        @Override
        public int compareTo(final Coin other) {
            return other.v - this.v;
        }
    }
}
