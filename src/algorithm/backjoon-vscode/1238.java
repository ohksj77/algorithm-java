import java.io.*;
import java.util.*;

class Main {
    private static List<List<Town>> arr;
    private static List<List<Town>> reverse;
    private static int N;
    private static int X;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
 
        arr = new ArrayList<>();
        reverse = new ArrayList<>();
 
        for (int i = 0; i <= N; i++) {
            arr.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }
 
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
 
            arr.get(start).add(new Town(end, weight));
            reverse.get(end).add(new Town(start, weight));
        }
 
        int[] dist1 = dijkstra(arr);
        int[] dist2 = dijkstra(reverse);

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, dist1[i] + dist2[i]);
        }
 
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(ans));
        bw.flush();
    }
    
    private static int[] dijkstra(List<List<Town>> towns) {
        Queue<Town> pq = new PriorityQueue<>();
        pq.offer(new Town(X, 0));
        
        boolean[] check = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;
 
        while (!pq.isEmpty()) {
            Town curTown = pq.poll();
            int cur = curTown.end;
 
            if (!check[cur]) {
                check[cur] = true;
 
                for (Town town : towns.get(cur)) {
                    if (!check[town.end] && dist[town.end] > dist[cur] + town.weight) {
                        dist[town.end] = dist[cur] + town.weight;
                        pq.add(new Town(town.end, dist[town.end]));
                    }
                }
            }
        }
        return dist;
    }

    private static class Town implements Comparable<Town> {
        private int end;
        private int weight;
    
        public Town(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    
        @Override
        public int compareTo(Town arg0) {
            return weight - arg0.weight;
        }
    }
}
