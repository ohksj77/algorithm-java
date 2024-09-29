package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Baekjoon13701 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final Set<String> set = new HashSet<>();
        final String[] input = br.readLine().split(" ");
        for (String s : input) {
            if (!set.contains(s)) {
                set.add(s);
                bw.write(s + " ");
            }
        }
        bw.flush();
    }
}
