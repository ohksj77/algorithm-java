import java.io.*;

class Main {

    private static int t;
    private static int n;
    private static int res;
    private static int[] students;
    private static boolean[] visited;
    private static boolean[] done;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= t; i++) {
            int n = Integer.parseInt(br.readLine());
            res = 0;
            String[] input = br.readLine().split(" ");

            students = new int[n + 1];
            done = new boolean[n + 1];
            visited = new boolean[n + 1];

            for (int j = 0; j < n; j++) {
                students[j + 1] = Integer.parseInt(input[j]);
            }

            for (int j = 1; j <= n; j++) {
                if (done[j]) {
                    continue;
                }
                dfs(j);
            }
            sb.append(Integer.toString(n - res)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void dfs(int idx) {
        if(done[idx]) {
            return;
        }
        if(visited[idx]) {
            done[idx] = true;
            res++;
        }
        visited[idx] = true;
        dfs(students[idx]);
        done[idx] = true;
        visited[idx] = false;
    }
}
