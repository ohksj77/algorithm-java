package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 십진수_삼진수 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int temp = 1;
        int sum = 0;

        while (n > 0) {
            sum += (n % 10) * temp;
            temp *= 3;
            n /= 10;
        }

        sum *= 22;

        final StringBuilder sb = new StringBuilder();

        while (sum > 0) {
            sb.insert(0, sum % 3);
            sum /= 3;
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
