package backjoon.first;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Baekjoon10816 {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] arr = br.readLine().split(" ");
        int[] sgList = new int[n];
        for (int i = 0; i < n; i++) {
            sgList[i] = Integer.parseInt(arr[i]);
        }
        Arrays.sort(sgList);
        int m = Integer.parseInt(br.readLine());
        List<Integer> cdList = Arrays.asList(br.readLine().split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());

        cdList.stream().forEach(i -> sb.append(upperBound(sgList, i) - lowerBound(sgList, i)).append(" "));

        bw.write(sb.toString());

        bw.flush();
        br.close();
        bw.close();

    }

    private static int lowerBound(int[] arr, int target) {
        int low = 0;
        int high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;
            if (target <= arr[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static int upperBound(int[] arr, int target) {
        int low = 0;
        int high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;
            if (target < arr[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

}
