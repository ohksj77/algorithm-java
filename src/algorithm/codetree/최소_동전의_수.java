package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 최소_동전의_수 {

    private static final int INF = 10_001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> plusCoins = new ArrayList<>();
        List<Integer> minusCoins = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int coin = Integer.parseInt(st.nextToken());
            if (coin > 0) {
                plusCoins.add(coin);
                continue;
            }
            minusCoins.add(coin);
        }

        int plusSize = plusCoins.size();
        int minusSize = minusCoins.size();

        int[] dp = new int[m * 2];
        for (int i = 1; i < m * 2; i++) {
            dp[i] = INF;
        }

        if (plusSize > 0) {
            for (int i = 1; i < m * 2; i++) {
                for (int j = 0; j < plusSize; j++) {
                    int coin = plusCoins.get(j);
                    int index = i - coin;
                    if (0 <= index && index < m * 2) {
                        dp[i] = Math.min(dp[i], dp[index] + 1);
                    }
                }
            }
        } else {
            bw.write("-1");
            bw.flush();
            return;
        }

        if (minusSize > 0) {
            for (int i = m * 2 - 1; i >= 0; i--) {
                for (int j = 0; j < minusSize; j++) {
                    int coin = minusCoins.get(j);
                    int index = i - coin;
                    if (0 <= index && index < m * 2) {
                        dp[i] = Math.min(dp[i], dp[index] + 1);
                    }
                }
            }
        }

        int answer = dp[m];

        if (answer == INF) {
            answer = -1;
        }
        bw.write(Integer.toString(answer));
        bw.flush();
    }
}
