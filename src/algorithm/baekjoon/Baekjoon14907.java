package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Baekjoon14907 {

    private static final int ALPHABET_SIZE = 26;
    private static final int[] elapseTimes = new int[ALPHABET_SIZE];
    private static final int[] workTimes = new int[ALPHABET_SIZE];
    private static final int[] precedeWorkCount = new int[ALPHABET_SIZE];
    private static final Set<Character> works = new HashSet<>();
    private static final Map<Character, List<Character>> followingWorks = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        String input;
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            st = new StringTokenizer(input);
            char work = st.nextToken().charAt(0);
            int time = Integer.parseInt(st.nextToken());
            String followingWork = "";

            if (st.hasMoreElements()) {
                followingWork = st.nextToken();
            }

            workTimes[work - 'A'] = time;
            works.add(work);

            for (char following : followingWork.toCharArray()) {
                followingWorks.computeIfAbsent(work, e -> new ArrayList<>()).add(following);
                precedeWorkCount[following - 'A']++;
            }
        }

        int answer = dijkstra();
        bw.write(Integer.toString(answer));
        bw.flush();
    }

    private static int dijkstra() {
        Queue<Character> q = new LinkedList<>();

        int answer = 0;
        for (char work : works) {
            int workIndex = work - 'A';
            if (precedeWorkCount[workIndex] == 0) {
                q.offer(work);
                elapseTimes[workIndex] = workTimes[workIndex];
                answer = Math.max(answer, elapseTimes[workIndex]);
            }
        }

        while (!q.isEmpty()) {
            char cur = q.poll();

            if (followingWorks.containsKey(cur)) {
                for (char nextWork : followingWorks.get(cur)) {
                    int nextWorkIndex = nextWork - 'A';
                    precedeWorkCount[nextWorkIndex]--;
                    if (precedeWorkCount[nextWorkIndex] == 0) {
                        q.offer(nextWork);
                    }

                    elapseTimes[nextWorkIndex] =
                            Math.max(
                                    elapseTimes[nextWorkIndex],
                                    elapseTimes[cur - 'A'] + workTimes[nextWorkIndex]);
                    answer = Math.max(answer, elapseTimes[nextWorkIndex]);
                }
            }
        }
        return answer;
    }
}
