package backjoon.first;

import java.io.*;

public class Baekjoon2263 {

    static int n;
    static int[] inOrderIndex, postOrder, inOrder;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        inOrder = new int[n + 1];
        postOrder = new int[n + 1];
        inOrderIndex = new int[n + 1];

        String[] in = br.readLine().split(" ");
        String[] post = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(in[i]);
            postOrder[i] = Integer.parseInt(post[i]);
            inOrderIndex[inOrder[i]] = i;
        }

        getPreOrder(0, n - 1, 0, n - 1);
        bw.append(sb.toString());
        bw.flush();

    }

    static void getPreOrder(int inStart, int inEnd, int postStart, int postEnd) {

        if (inStart > inEnd || postStart > postEnd) {
            return;
        }
        int root = postOrder[postEnd];
        sb.append(root + " ");
        int rootIndex = inOrderIndex[root];
        int left = rootIndex - inStart;
        getPreOrder(inStart, rootIndex - 1, postStart, postStart + left - 1);
        getPreOrder(rootIndex + 1, inEnd, postStart + left, postEnd - 1);

    }

}
