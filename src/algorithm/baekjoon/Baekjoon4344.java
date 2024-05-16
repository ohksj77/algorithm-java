package algorithm.baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Baekjoon4344 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int c = Integer.parseInt(br.readLine());
        for (int i = 0; i < c; i++) {
            List<Integer> list =
                    Arrays.asList(br.readLine().split(" ")).stream()
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
            int num = list.get(0);
            int sum = 0;
            for (int j = 1; j <= num; j++) {
                sum += list.get(j);
            }
            double avg = (sum * 1.0) / (num * 1.0);
            int count = 0;
            for (int k = 1; k <= num; k++) {
                if (list.get(k) > avg) {
                    count++;
                }
            }
            sb.append(String.format("%.3f", ((count * 1.0) / (num * 1.0)) * 100) + "%\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
