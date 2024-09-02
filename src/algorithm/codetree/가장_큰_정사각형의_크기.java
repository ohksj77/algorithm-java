package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class 가장_큰_정사각형의_크기 {

    private static final int COUNTABLE = 1;
    private static final List<int[]> board = new ArrayList<>();
    private static int n;
    private static int m;
    private static int max = 0;

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        for (int i = 0; i < n; i++) {
            final int[] line = makeLine(br.readLine());
            board.add(line);
        }

        for (int i = 0; i < n; i++) {
            final int[] line = board.get(i);
            for (int j = 0; j < m; j++) {
                final int value = line[j];
                if (value == COUNTABLE) {
                    max = Math.max(max, findSize(i, j, 2));
                }
            }
        }

        bw.write(Integer.toString(max * max));
        bw.flush();
    }

    private static int findSize(final int x, final int y, final int size) {
        if (x + size >= n || y + size >= m) {
            return size - 1;
        }

        boolean flag = false;

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (board.get(i)[j] != COUNTABLE) {
                    flag = true;
                    break;
                }
            }
        }

        if (flag) {
            return size - 1;
        }

        return findSize(x, y, size + 1);
    }

    private static int[] makeLine(final String line) {
        final String[] array = line.split(" ");
        final int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            result[i] = Integer.parseInt(array[i]);
        }
        return result;
    }
}
