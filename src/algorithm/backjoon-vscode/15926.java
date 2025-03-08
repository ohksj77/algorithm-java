import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] line = br.readLine().toCharArray();

        int[] length = new int[n];
        int max = 0;
        int before = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        int answer = 0;
        
        for (int i = 0; i < n; i++) {
            char c = line[i];
            if (c == '(') {
                stack.push(i);
                continue;
            }
            stack.pop();
            if (!stack.isEmpty()) {
                answer = Math.max(answer, i - stack.peek());
                continue;
            }
            stack.push(i);
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(answer));
        bw.flush();
    }
}
