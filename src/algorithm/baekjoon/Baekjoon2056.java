package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon2056 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] dp = new int[n + 1];

        List<Integer>[] list = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());

            dp[i] = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k; j++) {
                int to = Integer.parseInt(st.nextToken());
                list[i].add(to);
            }
        }

        for (int i = 1; i < n + 1; i++) {
            int max_time = 0;
            for (int value : list[i]) {
                if (max_time <= dp[value]) max_time = dp[value];
            }
            dp[i] += max_time;
        }

        int res = 0;
        for (int i = 1; i < n + 1; i++) {
            if (res <= dp[i]) res = dp[i];
        }

        bw.write(Integer.toString(res));
        bw.flush();
    }
}
