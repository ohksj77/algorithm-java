package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon11050 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final String[] NK = br.readLine().split(" ");

        bw.write(Integer.toString(binomial(Integer.parseInt(NK[0]), Integer.parseInt(NK[1]))));
        bw.flush();
    }

    private static int binomial(int n, int r) {
        if (r == 0 || n == r) {
            return 1;
        }
        return binomial(n - 1, r - 1) + binomial(n - 1, r);
    }
}
