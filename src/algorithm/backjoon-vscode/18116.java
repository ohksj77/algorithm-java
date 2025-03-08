import java.io.*;
import java.util.*;

class Main {

    private static int n;
    private static int[] root = new int[1000001];
    private static Set<Integer>[] set = new Set[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 1000000; i++) {
            root[i] = i;
            set[i] = new HashSet<>();
            set[i].add(i);
        }
        n = Integer.parseInt(st.nextToken());

        int tempRoot = 0;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            String query = st.nextToken();

            if (query.equals("I")) {
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());

                if (find(first) != find(second)) {
                    union(first, second);
                }
            } else {
                int value = Integer.parseInt(st.nextToken());
                tempRoot = find(value);
                sb.append(set[tempRoot].size()).append("\n");
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    private static int find(int target) {
        if (target == root[target]) {
            return target;
        }
        return root[target] = find(root[target]);
    }

    private static void union(int first, int second) {
        first = find(first);
        second = find(second);

        if (first < second) {
            root[second] = first;
            set[first].addAll(set[second]);
            return;
        }
        root[first] = second;
        set[second].addAll(set[first]);
    } 
}
