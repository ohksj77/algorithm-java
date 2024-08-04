package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class 코드트리_공장 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] input = br.readLine().split(" ");

        final int n = Integer.parseInt(input[0]);
        final int m = Integer.parseInt(input[1]);
        final int q = Integer.parseInt(input[2]);
        final int t = Integer.parseInt(input[3]);

        final List<Deque<Integer>> belts = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            belts.add(new ArrayDeque<>());
        }

        final int nm = n / m;

        for (int i = 0; i < n; i++) {
            final int value = Integer.parseInt(br.readLine());
            belts.get(i / nm).add(value);
        }

        for (int i = 0; i < q; i++) {
            final String[] now = br.readLine().split(" ");
            final int operation = Integer.parseInt(now[0]);
            final int a = Integer.parseInt(now[1]);

            if (operation == 1) {
                if (!belts.get(a).isEmpty()) {
                    final int item = belts.get(a).pollFirst();
                    belts.get(a).addLast(item);
                }
            } else if (operation == 2) {
                if (!belts.get(a).isEmpty()) {
                    final int item = belts.get(a).pollLast();
                    belts.get(a).addFirst(item);
                }
            } else if (operation == 3) {
                final int b = Integer.parseInt(now[2]);
                Deque<Integer> beltA = belts.get(a);
                Deque<Integer> beltB = belts.get(b);

                while (!beltA.isEmpty()) {
                    beltB.addFirst(beltA.pollLast());
                }
            }
        }

        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (final int value : belts.get(i)) {
                sum += value;
            }

            if (sum >= t) {
                bw.write("Yes");
                bw.newLine();
            } else {
                bw.write("No");
                bw.newLine();
            }
        }

        bw.flush();
    }
}
