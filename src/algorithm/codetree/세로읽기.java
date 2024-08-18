package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 세로읽기 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final char[][] words = new char[5][15];
        int maxLen = 0;

        for (int i = 0; i < 5; i++) {
            final char[] word = br.readLine().toCharArray();
            maxLen = Math.max(maxLen, word.length);
            for (int j = 0; j < word.length; j++) {
                words[i][j] = word[j];
            }
        }

        for (int i = 0; i < maxLen; i++) {
            for (int j = 0; j < 5; j++) {
                if (words[j][i] != 0) {
                    bw.write(words[j][i]);
                }
            }
        }

        bw.flush();
    }
}
