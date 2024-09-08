package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.IntConsumer;

public class Baekjoon12738 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        br.readLine();

        final List<Integer> list = new ArrayList<>();
        Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).forEach(search(list));

        bw.write(Integer.toString(list.size()));
        bw.flush();
    }

    private static IntConsumer search(final List<Integer> list) {
        return i -> {
            if (list.isEmpty() || list.get(list.size() - 1) < i) {
                list.add(i);
                return;
            }
            final int target = Collections.binarySearch(list, i);
            list.set(target >= 0 ? target : -target - 1, i);
        };
    }
}
