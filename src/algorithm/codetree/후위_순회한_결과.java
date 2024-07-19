package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 후위_순회한_결과 {

    private static final String BLANK = " ";
    private static int[] preOrder;
    private static int[] inOrder;
    private static int[] inOrderIndex;
    private static final StringBuilder SB = new StringBuilder();

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        preOrder = new int[n];
        inOrder = new int[n];
        inOrderIndex = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            preOrder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
            inOrderIndex[inOrder[i]] = i;
        }

        solve(0, n - 1, 0, n - 1);

        bw.write(SB.toString());
        bw.flush();
    }

    private static void solve(
            final int inStart, final int inEnd, final int preStart, final int preEnd) {
        if (inStart > inEnd || preStart > preEnd) {
            return;
        }

        final int root = preOrder[preStart];
        final int rootIndex = inOrderIndex[root];
        final int leftTreeSize = rootIndex - inStart;

        solve(inStart, rootIndex - 1, preStart + 1, preStart + leftTreeSize); // 오른쪽 서브 트리
        solve(rootIndex + 1, inEnd, preStart + leftTreeSize + 1, preEnd); // 왼쪽 서브 트리

        SB.append(root).append(BLANK);
    }
}
