package backjoon.baekjoon;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Baekjoon11625 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Map<Long, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Long cardNum = Long.parseLong(br.readLine());
            map.put(cardNum, map.getOrDefault(cardNum, 0) + 1);
        }
        int maxValue = 0;
        long answer = 0L;

        for (long key : map.keySet()) {
            int value = map.get(key);
            if (maxValue < value) {
                maxValue = value;
                answer = key;
            } else if (value == maxValue) {
                answer = Math.min(answer, key);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
