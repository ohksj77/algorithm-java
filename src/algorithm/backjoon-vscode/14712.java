import java.io.*;

class Main {

    private static int n;
    private static int m;
    private static boolean[][] visit;
	private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        visit = new boolean[n][m];

        find(0, 0);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(answer));
        bw.flush();
    }

    private static void find(int depth, int start) {
        answer += check(depth) ? 1 : 0;

		if (depth == n * m) {
			return;
        }

		for (int i = start; i < n * m; i++) {
			int r = i / m;
			int c = i % m;

			if (visit[r][c]) {
				continue;
            }
			visit[r][c] = true;
			find(depth + 1, i + 1);
			visit[r][c] = false;
		}
	}
	
	private static boolean check(int depth) {
		if (depth < 4) {
			return true;
        }
		
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < m - 1; j++) { 
				if (visit[i][j] && visit[i][j + 1] && visit[i + 1][j] && visit[i + 1][j + 1]) {
					return false;
                }
            }
        }
		return true;
    }
}
