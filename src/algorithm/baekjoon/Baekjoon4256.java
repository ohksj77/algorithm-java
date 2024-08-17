package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon4256 {

    private static final StringBuilder SB = new StringBuilder();
    private static int t;
    private static int n;
    private static int[] preOrder;
    private static int[] inOrder;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());

        for (int test = 0; test < t; test++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            preOrder = new int[n];
            inOrder = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                preOrder[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inOrder[i] = Integer.parseInt(st.nextToken());
            }

            findPostOrder(0, 0, n - 1);
            SB.append("\n");
        }
        bw.write(SB.toString());
        bw.flush();
    }

    private static void findPostOrder(final int rootIdx, final int begin, final int end) {
        if (rootIdx >= n) {
            return;
        }

        final int rootValue = preOrder[rootIdx];

        for (int idx = begin; idx <= end; idx++) {
            if (rootValue == inOrder[idx]) {
                findPostOrder(rootIdx + 1, begin, idx);
                findPostOrder(rootIdx + idx + 1 - begin, idx + 1, end);
                SB.append(rootValue).append(" ");
                return;
            }
        }
    }
}
