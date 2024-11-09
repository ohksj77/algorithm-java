package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Baekjoon2196 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] be = br.readLine().split(" ");

        int b = Integer.parseInt(be[0]);
        int e = Integer.parseInt(be[1]);
        int target = toNumber(br.readLine());
        int[] nums = new int[e];

        for (int i = 0; i < e; i++) {
            nums[i] = toNumber(br.readLine());
        }

        boolean[] visited = new boolean[1 << b];
        int[] dist = new int[1 << b];
        Arrays.fill(dist, 10000);

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < e; i++) {
            for (int j = 0; j < e; j++) {
                int value = nums[i] ^ nums[j];
                if (visited[value]) {
                    continue;
                }
                visited[value] = true;
                dist[value] = 1;
                q.offer(value);
            }
        }

        int d = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            d++;
            while (size-- > 0) {
                int value = q.poll();
                for (int i = 0; i < e; i++) {
                    int next = value ^ nums[i];

                    if (visited[next]) {
                        continue;
                    }
                    visited[next] = true;
                    dist[next] = d;
                    q.offer(next);
                }
            }
        }

        if (dist[target] < 10000) {
            bw.write(Integer.toString(dist[target]));
            bw.newLine();
            bw.write(toBinary(target, b));
            bw.flush();
            return;
        }

        visited = new boolean[1 << b];
        visited[target] = true;
        q.offer(target);
        int minDist = 10000;
        int num = 0;

        while (!q.isEmpty()) {
            int value = q.poll();

            for (int i = 0; i < b; i++) {
                int next = value ^ (1 << i);

                if (visited[next]) {
                    continue;
                }
                visited[next] = true;
                q.offer(next);

                if (dist[next] < minDist) {
                    num = next;
                    minDist = dist[next];
                } else if (dist[next] == minDist) {
                    if (num > next) {
                        num = next;
                    }
                }
            }
            if (minDist < 10000) {
                break;
            }
        }

        bw.write(Integer.toString(minDist));
        bw.newLine();
        bw.write(toBinary(num, b));
        bw.flush();
    }

    private static int toNumber(String num) {
        int result = 0;
        int x = 1;

        for (int i = num.length() - 1; i >= 0; i--) {
            if (num.charAt(i) == '1') {
                result += x;
            }
            x *= 2;
        }
        return result;
    }

    private static String toBinary(int num, int digit) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < digit; i++) {
            if (num % 2 == 1) {
                sb.insert(0, "1");
            } else {
                sb.insert(0, "0");
            }
            num /= 2;
        }
        return sb.toString();
    }
}
