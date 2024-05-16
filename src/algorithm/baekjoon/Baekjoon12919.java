package algorithm.baekjoon;

import java.io.*;

public class Baekjoon12919 {

    private static String s;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        String t = br.readLine();

        bw.write(String.valueOf(dfs(t)));

        bw.flush();
        br.close();
        bw.close();
    }

    static int dfs(String cur) {
        if (cur.length() == s.length()) {
            return cur.equals(s) ? 1 : 0;
        }
        int ret = 0;
        if (cur.charAt(0) == 'B')
            ret += dfs(new StringBuilder(cur.substring(1)).reverse().toString());
        if (cur.charAt(cur.length() - 1) == 'A') ret += dfs(cur.substring(0, cur.length() - 1));

        return ret > 0 ? 1 : 0;
    }
}
