package backjoon.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon1208 {
    private static int n;
    private static int s;
    private static long answer;
    private static List<Integer> leftList = new ArrayList<>();
    private static List<Integer> rightList = new ArrayList<>();
    private static int a[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        a = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        makeSum(0, 0, n / 2, leftList);
        makeSum(0, n / 2, n, rightList);

        Collections.sort(leftList);
        Collections.sort(rightList);

        findAns();

        if (s == 0) answer -= 1;

        System.out.println(answer);
    }

    static void findAns() {
        int pointL = 0;
        int pointR = rightList.size() - 1;

        while (pointL < leftList.size() && pointR >= 0) {
            int valL = leftList.get(pointL);
            int valR = rightList.get(pointR);

            if (valL + valR == s) {
                long cntL = 0;
                long cntR = 0;

                while (pointL < leftList.size() && valL == leftList.get(pointL)) {
                    cntL++;
                    pointL++;
                }
                while (pointR >= 0 && valR == rightList.get(pointR)) {
                    cntR++;
                    pointR--;
                }
                answer += cntL * cntR;
            }
            if (valL + valR < s) {
                pointL++;
            }
            if (valL + valR > s) {
                pointR--;
            }
        }
    }

    private static void makeSum(int sum, int start, int end, List<Integer> list) {
        if (start == end) {
            list.add(sum);
            return;
        }

        makeSum(sum, start + 1, end, list);
        makeSum(sum + a[start], start + 1, end, list);
    }
}
