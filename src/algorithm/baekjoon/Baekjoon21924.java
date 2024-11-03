package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Baekjoon21924 {

    private static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nm = br.readLine().split(" ");

        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        long originalWeight = 0;
        long newWeight = 0;

        PriorityQueue<int[]> edges = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        for (int i = 0; i < m; i++) {
            String[] abc = br.readLine().split(" ");
            int from = Integer.parseInt(abc[0]);
            int to = Integer.parseInt(abc[1]);
            int weight = Integer.parseInt(abc[2]);

            edges.add(new int[] {from, to, weight});
            originalWeight += weight;
        }

        int count = 0;
        while (!edges.isEmpty()) {
            int[] current = edges.poll();
            int from = current[0];
            int to = current[1];
            int weight = current[2];

            if (find(from) != find(to)) {
                newWeight += weight;
                union(from, to);
                count++;

                if (count == n - 1) {
                    break;
                }
            }
        }

        if (count != n - 1) {
            bw.write("-1");
        } else {
            bw.write(Long.toString(originalWeight - newWeight));
        }
        bw.flush();
    }

    private static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent < bParent) {
            parent[bParent] = aParent;
        } else if (bParent < aParent) {
            parent[aParent] = bParent;
        }
    }

    private static int find(int target) {
        if (parent[target] == target) {
            return target;
        }
        return parent[target] = find(parent[target]);
    }
}
