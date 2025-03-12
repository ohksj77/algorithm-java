import java.io.*;
import java.util.*;

class Main {

    private static int R;
    private static int C;
    private static int M;
    private static int answer = 0;
    private static Shark[][] sharks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sharks = new Shark[R][C];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(Integer.parseInt(st.nextToken()), 
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            sharks[r - 1][c - 1] = shark;
        }

        solution();
        System.out.println(answer);
    }

    private static void solution() {
        for (int i = 0; i < C; i++) {
            fishing(i);
            moveAllSharks();
        }
    }

    private static void fishing(int col) {
        for (int i = 0; i < R; i++) {
            if (sharks[i][col] != null) {
                answer += sharks[i][col].size;
                sharks[i][col] = null;
                return;
            }
        }
    }

    private static void moveAllSharks() {
        Shark[][] nextSharks = new Shark[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                moveShark(nextSharks, i, j);
            }
        }

        for (int i = 0; i < R; i++) {
            sharks[i] = Arrays.copyOf(nextSharks[i], C);
        }
    }

    private static void moveShark(Shark[][] nextSharks, int i, int j) {
        Shark shark = sharks[i][j];

        if (shark == null) {
            return;
        }

        int X = shark.direction < 3 ? R : C;
        int moveDistance = shark.speed % ((X - 1) * 2);
        int row = i;
        int col = j;

        for (int k = 0; k < moveDistance; k++) {
            if (shark.direction == 1) {
                row--;
                if (row < 0) {
                    shark.direction = 2;
                    row = 1;
                }
            } else if (shark.direction == 2) {
                row++;
                if (row > R - 1) {
                    shark.direction = 1;
                    row = R - 2;
                }
            } else if (shark.direction == 3) {
                col++;
                if (col > C - 1) {
                    shark.direction = 4;
                    col = C - 2;
                }
            } else {
                col--;
                if (col < 0) {
                    shark.direction = 3;
                    col = 1;
                }
            }
        }

        if (nextSharks[row][col] != null && nextSharks[row][col].size > shark.size) {
            return;
        }

        nextSharks[row][col] = shark;
    }


    private static class Shark {
        private int speed; 
        private int direction;
        private int size;

        public Shark(int speed, int direction, int size) {
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }
}
