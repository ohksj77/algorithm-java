package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon2357 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(arr);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int[] result = segmentTree.query(a - 1, b - 1);
            bw.write(result[0] + " " + result[1]);
            bw.newLine();
        }
        bw.flush();
    }

    private static class SegmentTree {
        private final int n;
        private final int[] arr;
        private final int[][] tree;

        public SegmentTree(int[] arr) {
            this.n = arr.length;
            this.arr = arr;
            this.tree = new int[n * 4][2];
            init(0, n - 1, 1);
        }

        private int[] init(int start, int end, int node) {
            if (start == end) {
                return tree[node] = new int[] {arr[start], arr[start]};
            }

            int mid = (start + end) / 2;
            int[] left = init(start, mid, node * 2);
            int[] right = init(mid + 1, end, node * 2 + 1);

            return tree[node] =
                    new int[] {Math.min(left[0], right[0]), Math.max(left[1], right[1])};
        }

        public int[] query(int left, int right) {
            return query(left, right, 0, n - 1, 1);
        }

        private int[] query(int left, int right, int start, int end, int node) {
            if (left > end || right < start) {
                return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE};
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            int[] leftResult = query(left, right, start, mid, node * 2);
            int[] rightResult = query(left, right, mid + 1, end, node * 2 + 1);

            return new int[] {
                Math.min(leftResult[0], rightResult[0]), Math.max(leftResult[1], rightResult[1])
            };
        }
    }
}
