package backjoon.first;

import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon1790 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());

        long count = 9, length = 1, target = 0;

        while (k > count * length) {
            k -= length * count;
            target += count;
            length++;
            count *= 10;
        }

        target = target + 1 + (k - 1) / length;

        if (target > n) bw.write(String.valueOf(-1));
        else bw.write(String.valueOf(target).charAt((int) ((k - 1) % length)));
        bw.flush();
    }
}
