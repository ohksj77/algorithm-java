package backjoon.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Baekjoon1920 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        final String[] A = br.readLine().split(" ");
        final int M = Integer.parseInt(br.readLine());
        final String[] B = br.readLine().split(" ");

        final Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(A[i]);
        }

        for (int i = 0; i < M; i++) {
            if (set.contains(B[i])) {
                bw.write("1\n");
                continue;
            }
            bw.write("0\n");
        }

        bw.flush();
    }
}
