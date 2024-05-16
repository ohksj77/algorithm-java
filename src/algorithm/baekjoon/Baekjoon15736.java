package algorithm.baekjoon;

import java.io.*;

public class Baekjoon15736 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int input = Integer.parseInt(br.readLine());
        final int answer = (int) Math.sqrt(input);

        bw.write(Integer.toString(answer));
        bw.flush();
    }
}
