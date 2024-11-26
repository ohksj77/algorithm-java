package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon1275 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int q = Integer.parseInt(nm[1]);

        String[] input = br.readLine().split(" ");

        long[] numbers = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            numbers[i] = Long.parseLong(input[i - 1]);
        }

        SegmentTree segmentTree = new SegmentTree(numbers, n);

        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if (a <= b) {
                bw.write(Long.toString(segmentTree.query(1, n, 1, a, b)));
            } else {
                bw.write(Long.toString(segmentTree.query(1, n, 1, b, a)));
            }
            segmentTree.calculate(d, c, n);
            bw.newLine();
        }
        bw.flush();
    }

    private static class SegmentTree {
        private final long[] nums;
        private final long[] tree;

        public SegmentTree(long[] nums, int n) {
            this.nums = nums;
            int k = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
            int size = (int) Math.pow(2, k);
            this.tree = new long[size];
            init(1, n, 1);
        }

        private long init(int s, int e, int node) {
            if (s == e) {
                return tree[node] = nums[s];
            }
            int mid = (s + e) >>> 1;

            return tree[node] = init(s, mid, node * 2) + init(mid + 1, e, node * 2 + 1);
        }

        public void calculate(int d, int c, int n) {
            long diff = d - nums[c];
            nums[c] = d;
            update(1, n, 1, c, diff);
        }

        public void update(int s, int e, int node, int target, long diff) {
            if (target < s || e < target) {
                return;
            }
            tree[node] += diff;
            if (s == e) {
                return;
            }
            int mid = (s + e) >>> 1;
            update(s, mid, node * 2, target, diff);
            update(mid + 1, e, node * 2 + 1, target, diff);
        }

        public long query(int s, int e, int node, int L, int R) {
            if (R < s || e < L) {
                return 0;
            }
            if (L <= s && e <= R) {
                return tree[node];
            }

            int mid = (s + e) >>> 1;
            return query(s, mid, node * 2, L, R) + query(mid + 1, e, node * 2 + 1, L, R);
        }
    }
}
