package backjoon.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1259 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            final StringBuilder input = new StringBuilder().append(br.readLine());
            if (input.toString().equals("0")) {
                break;
            }
            if (input.toString().equals(input.reverse().toString())) {
                bw.write("yes\n");
                continue;
            }
            bw.write("no\n");
        }
        bw.flush();
    }
}
