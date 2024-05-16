package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Baekjoon1201 {

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (n < m + k - 1 || n > m * k) {
            bw.write("-1");
        } else {
            bw.write(
                    String.valueOf(
                            sb.append(
                                    ans(n, m, k).stream()
                                            .map(String::valueOf)
                                            .collect(Collectors.joining(" ")))));
        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static List<Integer> ans(int n, int m, int k) {
        List<Integer> arr = IntStream.iterate(k, i -> i > 0, i -> i - 1).boxed().collect(toList());
        n -= k;
        m -= 1;
        while (m != 0) {
            int nm = n / m;
            int tmpK = k;
            IntStream.iterate(tmpK + nm, i -> i > tmpK, i -> i - 1).forEach(arr::add);
            k += nm;
            n -= nm;
            m -= 1;
        }
        return arr;
    }
}
