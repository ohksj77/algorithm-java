package backjoon.first;

import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon2873 {

    static int r, c;
    static StringBuilder sb = new StringBuilder();
    static String[][] board;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st1.nextToken());
        c = Integer.parseInt(st1.nextToken());

        board = new String[r][c];

        if (r % 2 != 0) {
            for (int i = 0; i < r / 2; i++) rightDownLeftDown(c);
            for (int i = 0; i < c - 1; i++) sb.append('R');
            System.out.println(sb);
            return;
        }
        if (c % 2 != 0) {
            for (int i = 0; i < c / 2; i++) downRightUpRight(r);
            for (int i = 0; i < r - 1; i++) sb.append('D');
            System.out.println(sb);
            return;
        }

        int tr = 0, tc = 0;
        int min = 1000;
        for(int i = 0; i < r; i++) {
            board[i] = br.readLine().split(" ");
            for (int j = 0; j < c; j++) {
                int cur = Integer.parseInt(board[i][j]);
                if ((i + j) % 2 == 0 || min <= cur) continue;
                min = cur;
                tr = i + 1;
                tc = j + 1;
            }
        }

        for (int i = 0; i < (tr - 1) / 2; i++) rightDownLeftDown(c);
        for (int i = 0; i < (tc - 1) / 2; i++) sb.append('D').append('R').append('U').append('R');

        if (tr % 2 == 1) sb.append('D').append('R');
        else sb.append('R').append('D');

        int len = (c - tc) / 2;

        if (len != 0) sb.append('R');

        for (int i = 0; i < len; i++) {
            sb.append('U').append('R').append('D');
            if (i != len-1)  sb.append('R');
        }

        len = (r - tr) / 2;

        if (len != 0) sb.append('D');

        for (int i = 0; i < len; i++) {
            leftDownRight(c);
            if (i != len-1) sb.append('D');
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        br.close();
        bw.close();
    }

    static void rightDownLeftDown(int c) {
        for (int i = 0; i < c - 1; i++) sb.append('R');
        sb.append('D');
        for (int i = 0; i < c - 1; i++) sb.append('L');
        sb.append('D');
    }

    static void downRightUpRight(int r) {
        for (int i = 0; i < r - 1; i++) sb.append('D');
        sb.append('R');
        for (int i = 0; i < r - 1; i++) sb.append('U');
        sb.append('R');
    }

    static void leftDownRight(int c) {
        for (int i = 0; i < c - 1; i++) sb.append('L');
        sb.append('D');
        for (int i = 0; i < c - 1; i++) sb.append('R');
    }

}