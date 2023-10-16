package backjoon.first;

import java.io.*;
import java.util.Arrays;

public class Baekjoon11728 {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        String[] arr1 = br.readLine().split(" ");
        String[] arr2 = br.readLine().split(" ");


        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[] res = new int[n + m];
        int idx = 0;

        for (int i = 0; i < n; i++) {
            res[idx++] = Integer.parseInt(arr1[i]);
        }
        for (int j = 0; j < m; j++) {
            res[idx++] = Integer.parseInt(arr2[j]);
        }

        Arrays.sort(res);

        for (int k = 0; k < n + m; k++) {
            bw.write(res[k] + " ");
        }

        bw.flush();
        br.close();
        bw.close();

    }

}
