import java.io.*;

class Main {

    private static final long DIV = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		long[] d = new long[n + 1];

		d[0] = 1;
		d[1] = 0;

		for (int i = 2; i <= n; i += 2) {
			for (int j = 0; j < i / 2; ++j) {
				d[i] += (d[0 + (j * 2)] * d[i - 2 - (j * 2)]) % DIV;
				d[i] %= DIV;
			}
		}
				
		bw.write(Long.toString(d[n]));
        bw.flush();
    }
}
