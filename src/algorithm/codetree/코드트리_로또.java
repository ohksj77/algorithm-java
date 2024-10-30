package algorithm.codetree;

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class 코드트리_로또 {

    private static final StringBuilder result = new StringBuilder();
    private static final String NEW_LINE = "\n";
    private static final String DELIMITER = " ";
    private static int n;
    private static int m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        search(1, new ArrayList<>());

        bw.write(result.toString());
        bw.flush();
    }

    private static void search(int index, List<Integer> path) {
        if (path.size() == m) {
            result.append(
                    path.stream().map(String::valueOf).collect(Collectors.joining(DELIMITER)));
            result.append(NEW_LINE);
            return;
        }

        for (int i = index; i <= n; i++) {
            path.add(i);
            search(i + 1, path);
            path.remove(path.size() - 1);
        }
    }
}
