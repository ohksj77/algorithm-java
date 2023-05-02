package backjoon.study.fourth;

import java.io.*;
import java.util.*;

public class TripPlan {

    private static int n, m;
    private static int[] parent = new int[501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    unionParent(i + 1, j + 1);
                }
            }
        }

        List<Integer> plan = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int x = Integer.parseInt(st.nextToken());
            plan.add(x);
        }

        boolean result = true;
        for (int i = 0; i < m - 1; i++) {
            if (findParent(plan.get(i)) != findParent(plan.get(i + 1))) {
                result = false;
            }
        }

        bw.write(result ? "YES" : "NO");
        bw.flush();
    }

    private static int findParent(int x) {
        if (x == parent[x]) return x;
        parent[x] = findParent(parent[x]);
        return parent[x];
    }

    private static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }
}

/**

 5 4
 0 1 0 1 1
 1 0 1 1 0
 0 1 0 0 0
 1 1 0 0 0
 1 0 0 0 0
 2 3 4 3

 */
