package algorithm.baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baekjoon13549 {
    static final int max = 100001;
    static int N, K;
    static Scanner sc = new Scanner(System.in);
    static boolean[] visit;
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        visit = new boolean[max];
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        K = Integer.parseInt(line[1]);
        visit[N] = true;
        bw.write(Integer.toString(bfs(new Node(N, 0))));
        bw.flush();
    }

    private static int bfs(Node root) {
        if (root.x == K) return 0;
        queue.add(root);

        while (!queue.isEmpty()) {
            Node n = queue.poll();

            if (n.x * 2 <= max) {
                if (!visit[n.x * 2]) {
                    if (n.x * 2 == K) return n.sec;
                    else {
                        visit[n.x * 2] = true;
                        queue.add(new Node(n.x * 2, n.sec));
                    }
                }
            }

            if (n.x > 0) {
                if (!visit[n.x - 1]) {
                    if (n.x - 1 == K) return n.sec + 1;
                    else {
                        visit[n.x - 1] = true;
                        queue.add(new Node(n.x - 1, n.sec + 1));
                    }
                }
            }

            if (n.x < max - 1) {
                if (!visit[n.x + 1]) {
                    if (n.x + 1 == K) return n.sec + 1;
                    else {
                        visit[n.x + 1] = true;
                        queue.add(new Node(n.x + 1, n.sec + 1));
                    }
                }
            }
        }
        return -1;
    }

    static class Node {
        int x;
        int sec;

        Node(int newX, int newSec) {
            this.x = newX;
            this.sec = newSec;
        }
    }
}
