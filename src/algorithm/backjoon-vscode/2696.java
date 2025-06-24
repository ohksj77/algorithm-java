import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        int t = Integer.parseInt(br.readLine());
 
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine()); 
            sb.append(((n + 1) / 2) + "\n");

            ArrayList<Integer> a = new ArrayList<>();
            int count = 0;
 
            for (int i = 0; i < n; i++) {
                if (i % 10 == 0) {
                    st = new StringTokenizer(br.readLine());
                }
                int x = Integer.parseInt(st.nextToken());
                a.add(x);
                Collections.sort(a);
                
                if (i % 2 == 0) {
                    if (count == 9 || i == n - 1) {
                        sb.append(a.get(i / 2) + "\n");
                        count = 0;
                    } else {
                        sb.append(a.get(i / 2) + " ");
                    }
                    count++;
                }
            }
        }
 
        bw.write(sb.toString());
        bw.flush();
    }
}
