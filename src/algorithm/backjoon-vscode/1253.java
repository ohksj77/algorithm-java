import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0 ; i < n ; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);
        int result = 0;

        for (int i = 0 ; i < n ; i++){
            int left = 0;
            int right = n - 1;
            
            while (true) {
                if (left == i) {
                    left++;
                } else if (right == i) {
                    right--;
                }
                if (left >= right) {
                    break;
                }

                if (numbers[left] + numbers[right] > numbers[i]) {
                    right--;
                } else if (numbers[left] + numbers[right] < numbers[i]) {
                    left++;
                } else {
                    result++;
                    break;
                }
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(result));
        bw.flush();
    }
}