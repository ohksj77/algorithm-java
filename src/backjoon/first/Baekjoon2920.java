package backjoon.first;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon2920 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int first = Integer.parseInt(st.nextToken());

        boolean flag = true;

        if (first == 1) {
            for (int i = 2; i <= 8; i++) {
                if (Integer.parseInt(st.nextToken()) != i) {
                    flag = false;
                    break;
                }
            }
        } else if (first == 8) {
            for (int i = 7; i >= 1; i--) {
                if (Integer.parseInt(st.nextToken()) != i) {
                    flag = false;
                    break;
                }
            }
        }

        if (first == 1 && flag) {
            bw.write("ascending");
        } else if (first == 8 && flag) {
            bw.write("descending");
        } else {
            bw.write("mixed");
        }

        bw.flush();
    }
}
