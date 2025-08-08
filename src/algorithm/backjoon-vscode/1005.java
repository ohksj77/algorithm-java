
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            final int[] width = new int[N + 1];
            final int[] count = new int[N + 1];
            final List<List<Integer>> graph = new ArrayList<>();

            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }
            st = new StringTokenizer(br.readLine(), " ");

            for (int i = 1; i <= N; i++) {
                width[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                count[B]++;
                graph.get(A).add(B);
            }
            int W = Integer.parseInt(br.readLine());
            Queue<Integer> q = new LinkedList<>();
            final int[] answer = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                answer[i] = width[i];
                if (count[i] == 0) q.offer(i);
            }
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int next : graph.get(cur)) {
                    answer[next] = Math.max(answer[next], answer[cur] + width[next]);
                    count[next]--;
                    if (count[next] == 0) {
                        q.offer(next);
                    }
                }
            }
            sb.append(answer[W]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
