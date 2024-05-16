package algorithm.study.fifth;

import java.io.*;

public class Baekjoon1439 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String num = br.readLine().strip();
        int zeros = 0;
        int ones = 0;
        if (num.charAt(0) == '0') zeros++;
        else ones++;

        for (int i = 1; i < num.length(); i++) {
            char cur = num.charAt(i);
            if (num.charAt(i - 1) != cur) {
                if (cur == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }
        }
        bw.write(Integer.toString(Math.min(zeros, ones)));
        bw.flush();
    }
}
