package backjoon.first;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon10815 {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
        int m = Integer.parseInt(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st1.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 0; i < m; i++) {
            int result = binarySearch(Integer.parseInt(st2.nextToken()), arr);
            if (result != -1) {
                sb.append("1 ");
            } else {
                sb.append("0 ");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static int binarySearch(int target, int[] array) {
        int left = 0, right = array.length - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (array[mid] < target) {
                left = mid + 1;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
