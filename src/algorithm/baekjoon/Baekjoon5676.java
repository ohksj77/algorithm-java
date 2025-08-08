package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon5676 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s;

        while ((s = br.readLine()) != null) {
            if (s.isEmpty()) {
                break;
            }
            final String[] nm = s.split(" ");
            final int n = Integer.parseInt(nm[0]);
            final int m = Integer.parseInt(nm[1]);

            final SegmentTree tree = new SegmentTree(n);
            long[] arr = new long[n + 1];

            final String[] input = br.readLine().split(" ");

            for (int i = 1; i <= n; i++) {
                arr[i] = Long.parseLong(input[i - 1]);
            }

            tree.init(arr, 1, 1, n);

            for (int i = 0; i < m; i++) {
                final String[] values = br.readLine().split(" ");

                String string = values[0];
                if (string.equals("C")) {
                    tree.update(1, 1, n, Integer.parseInt(values[1]), Integer.parseInt(values[2]));
                } else if (string.equals("P")) {
                    long ans =
                            tree.mul(
                                    1,
                                    1,
                                    n,
                                    Integer.parseInt(values[1]),
                                    Integer.parseInt(values[2]));
                    if ((int) ans == 1) {
                        bw.write("+");
                    } else if ((int) ans == 0) {
                        bw.write("0");
                    } else if ((int) ans == -1) {
                        bw.write("-");
                    }
                }
            }
            bw.newLine();
            bw.flush();
        }

        br.close();
        bw.close();
    }

    private static class SegmentTree {
        private final long[] st;

        public SegmentTree(final int n) {
            final double height = Math.ceil(Math.log(n) / Math.log(2)) + 1;
            final long node = Math.round(Math.pow(2, height));
            st = new long[Math.toIntExact(node)];
        }

        public long init(final long[] arr, final int node, final int start, final int end) {
            if (start == end) {
                return st[node] = (arr[start] > 0) ? 1 : (arr[start] < 0) ? -1 : 0;
            }
            return st[node] =
                    init(arr, node * 2, start, (start + end) / 2)
                            * init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
        }

        public long mul(
                final int node, final int start, final int end, final int left, final int right) {
            if (end < left || right < start) {
                return 1;
            }
            if (left <= start && end <= right) {
                return st[node];
            }
            return mul(node * 2, start, (start + end) / 2, left, right)
                    * mul(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        }

        public long update(
                final int node,
                final int start,
                final int end,
                final int index,
                final long changeValue) {
            if (index < start || end < index) {
                return st[node];
            }
            if (start == index && end == index) {
                return st[node] = changeValue > 0 ? 1 : changeValue < 0 ? -1 : 0;
            }
            return st[node] =
                    update(node * 2, start, (start + end) / 2, index, changeValue)
                            * update(node * 2 + 1, (start + end) / 2 + 1, end, index, changeValue);
        }
    }
}
