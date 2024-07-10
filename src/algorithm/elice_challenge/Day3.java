package algorithm.elice_challenge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Day3 {
    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] input = br.readLine().split("");

        final Deque<String> deque = new ArrayDeque<>();

        int index = 0;
        int answer = 0;

        while (index < input.length) {
            String now = input[index++];

            if (!now.equals(")")) {
                deque.add(now);
                continue;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(deque.pollLast());
            while (!deque.isEmpty()) {
                if (now.equals("(")) {
                    break;
                }
                if (!now.equals(")")) {
                    sb.append(now);
                }
                now = deque.pollLast();
            }

            final int numberLength = sb.length();
            if (deque.isEmpty()) {
                break;
            }
            final int k = Integer.parseInt(deque.pollLast());
            final int add = numberLength * k;

            for (int i = 0; i < add; i++) {
                deque.add("1");
            }
            answer = deque.size();
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }
}
