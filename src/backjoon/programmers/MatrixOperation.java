package backjoon.programmers;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class MatrixOperation {

    public static void main(String[] args) {
        System.out.println(
                Arrays.stream(
                                new MatrixOperation()
                                        .solution(
                                                new int[][] {
                                                    {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
                                                },
                                                new String[] {
                                                    "ShiftRow", "Rotate", "ShiftRow", "Rotate"
                                                }))
                        .map(Arrays::toString)
                        .collect(Collectors.joining())); // [[1,6,7,8],[5,9,10,4],[2,3,12,11]]

        System.out.println(
                Arrays.stream(
                                new MatrixOperation()
                                        .solution(
                                                new int[][] {{1, 2}, {3, 4}},
                                                new String[] {
                                                    "ShiftRow", "Rotate", "ShiftRow", "Rotate"
                                                }))
                        .map(Arrays::toString)
                        .collect(Collectors.joining()));
    }

    private final Deque<Integer> left = new ArrayDeque<>();
    private final Deque<Integer> right = new ArrayDeque<>();
    private final Deque<Deque<Integer>> middle = new ArrayDeque<>();

    public int[][] solution(int[][] rc, String[] operations) {
        for (int i = 0; i < rc.length; i++) {
            middle.add(new ArrayDeque<>());
            for (int j = 0; j < rc[0].length; j++) {
                if (j == 0) {
                    left.add(rc[i][j]);
                    continue;
                }
                if (j == rc[0].length - 1) {
                    right.add(rc[i][j]);
                    continue;
                }
                middle.getLast().add(rc[i][j]);
            }
        }

        for (int i = 0; i < operations.length; i++) {
            if (operations[i].equals("Rotate")) {
                rotate();
                continue;
            }
            shiftRow();
        }

        for (int i = 0; i < rc.length; i++) {
            Deque<Integer> nowMiddle = middle.removeFirst();
            for (int j = 0; j < rc[0].length; j++) {
                if (j == 0 && !left.isEmpty()) {
                    rc[i][j] = left.removeFirst();
                    continue;
                }
                if (j == rc[0].length - 1 && !right.isEmpty()) {
                    rc[i][j] = right.removeFirst();
                    continue;
                }
                if (!nowMiddle.isEmpty()) {
                    rc[i][j] = nowMiddle.removeFirst();
                }
            }
        }
        return rc;
    }

    private void shiftRow() {
        left.addFirst(left.removeLast());
        right.addFirst(right.removeLast());
        middle.addFirst(middle.removeLast());
    }

    private void rotate() {
        Integer first = left.getFirst();
        Integer last = right.getLast();

        if (middle.getFirst().isEmpty()) {
            left.removeFirst();
            left.addLast(last);

            right.removeLast();
            right.addFirst(first);
            return;
        }

        left.removeFirst();
        left.addLast(middle.getLast().getFirst());

        right.removeLast();
        right.addFirst(middle.getFirst().getLast());

        middle.getFirst().removeLast();
        middle.getFirst().addFirst(first);

        middle.getLast().removeFirst();
        middle.getLast().addLast(last);
    }
}
