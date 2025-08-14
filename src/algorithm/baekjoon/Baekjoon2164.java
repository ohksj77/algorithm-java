package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baekjoon2164 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        int p = Integer.highestOneBit(n);

        int answer;
        if (n == p) {
            answer = n;
        } else {
            answer = 2 * (n - p);
        }

        System.out.println(answer);
    }
}
