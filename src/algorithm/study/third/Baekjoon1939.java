package algorithm.study.third;

import java.io.*;
import java.util.*;

public class Baekjoon1939 {
    private static int n, m, a, b, start, end;
    private static List<Bridge>[] list;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new List[n + 1];
        for (int i = 0; i < n + 1; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Bridge(b, c));
            list[b].add(new Bridge(a, c));
            end = Math.max(end, c);
        }
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        while (start <= end) {
            int middle = (start + end) / 2;
            if (bfs(middle)) start = middle + 1;
            else end = middle - 1;
        }
        bw.write(String.valueOf(end));
        bw.flush();
    }

    private static boolean bfs(int limit) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        q.offer(a);
        visited[a] = true;

        while (!q.isEmpty()) {
            int front = q.poll();
            if (front == b) return true;

            for (int i = 0; i < list[front].size(); i++) {
                Bridge bridge = list[front].get(i);
                int to = bridge.to;
                int weight = bridge.weight;
                if (visited[to] || weight < limit) continue;
                visited[to] = true;
                q.offer(to);
            }
        }
        return false;
    }

    private static class Bridge {
        int to;
        int weight;

        public Bridge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
