package algorithm.study.first;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon11000 {
    private static Lecture[] lectures;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        lectures = new Lecture[n];
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(lectures, (lec1, lec2) -> lec1.start == lec2.start ? lec1.end - lec2.end : lec1.start - lec2.start);

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(lectures[0].end);

        for (int i = 1; i < n; i++) {
            if (queue.peek() <= lectures[i].start) {
                queue.poll();
            }
            queue.offer(lectures[i].end);
        }

        bw.write(String.valueOf(queue.size()));
        bw.flush();
    }

    private static class Lecture {
        int start;
        int end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
