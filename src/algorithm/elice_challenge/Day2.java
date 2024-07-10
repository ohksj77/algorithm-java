package algorithm.elice_challenge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

class Day2 {
    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int m = Integer.parseInt(br.readLine().split(" ")[1]);

        final int[] array = toIntArray(br.readLine().split(" "));

        for (int i = 0; i < m; i++) {
            final int[] ijk = toIntArray(br.readLine().split(" "));
            bw.write(Integer.toString(calculate(ijk, array)));
            bw.newLine();
        }
        bw.flush();
    }

    private static int[] toIntArray(final String[] array) {
        return Arrays.stream(array).mapToInt(Integer::parseInt).toArray();
    }

    private static int calculate(final int[] ijk, final int[] target) {
        final int i = ijk[0];
        final int j = ijk[1];
        final int k = ijk[2];

        final int[] array = new int[j - i + 1];
        int temp = 0;

        for (int index = i - 1; index < j; index++) {
            array[temp++] = target[index];
        }
        Arrays.sort(array);

        return array[k - 1];
    }
}
