package backjoon.first;

import java.io.*;

public class Baekjoon1074 {

    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]), r = Integer.parseInt(input[1]),
                c = Integer.parseInt(input[2]), size = (int) Math.pow(2, n);

        find(size, r, c);

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();

    }

    private static void find(int size, int r, int c) {
        if (size == 1) {
            return;
        }
        if (r < size / 2 && c < size / 2) {
            find(size / 2, r, c);
        } else if (r < size / 2 && c >= size / 2) {
            count += size * size / 4;
            find(size / 2, r, c - size / 2);
        } else if (r >= size / 2 && c < size / 2) {
            count += (size * size / 4) * 2;
            find(size / 2, r - size / 2, c);
        } else {
            count += (size * size / 4) * 3;
            find(size / 2, r - size / 2, c - size / 2);
        }
    }

}
