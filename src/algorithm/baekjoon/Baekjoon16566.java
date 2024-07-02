package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon16566 {
    private static int m, k;
    private static int[] cards;
    private static int[] magician;

    private static void input() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] str = br.readLine().split(" ");

        m = Integer.parseInt(str[1]);
        k = Integer.parseInt(str[2]);
        cards = new int[m];
        magician = new int[k];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < k; i++) {
            magician[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static int binarySearch(int n) {
        int start = m - 1;
        int end = -1;
        while (start > end + 1) {
            int mid = (start + end) / 2;
            if (cards[mid] > n) {
                start = mid;
                continue;
            }
            end = mid;
        }
        return start;
    }

    private static int find(int n, int[] set) {
        if (set[n] < 0) {
            return n;
        }
        return set[n] = find(set[n], set);
    }

    private static void union(int a, int b, int[] set) {
        if (b >= m) {
            return;
        }
        set[a] = b;
    }

    public static void main() throws IOException {
        input();

        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int[] set = new int[m];
        Arrays.fill(set, -1);

        for (int card : magician) {
            int position = binarySearch(card);
            position = find(position, set);
            union(position, position + 1, set);
            bw.write(Integer.toString(cards[position]));
            bw.newLine();
            bw.flush();
        }
    }
}
