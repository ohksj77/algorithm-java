package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2531 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nkds = br.readLine().split(" ");

        int n = Integer.parseInt(nkds[0]);
        int d = Integer.parseInt(nkds[1]);
        int k = Integer.parseInt(nkds[2]);
        int c = Integer.parseInt(nkds[3]);

        int[] sushi = new int[n];

        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int[] count = new int[d + 1];

        int answer = 0;
        int sum = 0;

        for (int i = 0; i < k; i++) {
            if (count[sushi[i]] == 0) {
                sum++;
            }
            count[sushi[i]]++;
        }

        answer = sum;

        for (int i = 0; i < n; i++) {
            if (count[c] == 0) {
                answer = Math.max(answer, sum + 1);
            } else {
                answer = Math.max(answer, sum);
            }

            count[sushi[i]]--;
            if (count[sushi[i]] == 0) {
                sum--;
            }

            int next = (i + k) % n;
            if (count[sushi[next]] == 0) {
                sum++;
            }
            count[sushi[next]]++;
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }
}
