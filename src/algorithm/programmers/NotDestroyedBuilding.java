package algorithm.programmers;

public class NotDestroyedBuilding { // 테스트 실행을 위한 클래스입니다.

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.solution(
                new int[][]{{5,5,5,5,5}, {5,5,5,5,5}, {5,5,5,5,5}, {5,5,5,5,5}},
                new int[][]{{1,0,0,3,4,4}, {1,2,0,2,3,2}, {2,1,0,3,1,2}, {1,0,1,3,3,1}}));

        System.out.println(solution.solution(
                new int[][]{{1,2,3}, {4,5,6}, {7,8,9}},
                new int[][]{{1,1,1,2,2,4}, {1,0,0,1,1,2}, {2,2,0,2,0,100}}));
    }
}

class Solution {

    private static final int ATTACK_SKILL = 1;
    private static final int DESTROY_LIMIT = 0;
    private int columnLength;
    private int rowLength;

    public int solution(int[][] board, int[][] skill) {

        columnLength = board[0].length;
        rowLength = board.length;

        int[][] prefixSumArray = makePrefixSumArray(skill);

        return prefixSum(board, prefixSumArray);
    }

    private int[][] makePrefixSumArray(int[][] skill) { // 누적합을 수행하기 위한 배열 생성
        int[][] prefixSumArray = new int[rowLength + 1][columnLength + 1];

        for (int[] nowSkill : skill) {
            int r1 = nowSkill[1];
            int c1 = nowSkill[2];
            int r2 = nowSkill[3];
            int c2 = nowSkill[4];
            int degree = nowSkill[0] == ATTACK_SKILL ? -nowSkill[5] : nowSkill[5]; // 공격과 회복 여부에 따른 degree 값 설정

            prefixSumArray[r1][c1] += degree;
            prefixSumArray[r1][c2 + 1] -= degree;
            prefixSumArray[r2 + 1][c1] -= degree;
            prefixSumArray[r2 + 1][c2 + 1] += degree;
        }

        return prefixSumArray;
    }

    private int prefixSum(int[][] board, int[][] prefixSumArray) { // 누적합 수행
        int result = 0;

        for (int j = 0; j < columnLength; j++) { // 행(가로) 방향 누적합
            for (int i = 1; i < rowLength; i++) {
                prefixSumArray[i][j] += prefixSumArray[i - 1][j];
            }
        }

        for (int i = 0; i < rowLength; i++) { // 열(세로) 방향 누적합
            for (int j = 1; j < columnLength; j++) {
                prefixSumArray[i][j] += prefixSumArray[i][j - 1];
            }
        }

        for (int i = 0; i < rowLength; i++) { // 누적합 배열을 주어진 배열에 반영
            for (int j = 0; j < columnLength; j++) {
                board[i][j] += prefixSumArray[i][j];

                if (board[i][j] > DESTROY_LIMIT) { // 파괴되지 않았다면 result(최종 결과) 증가
                    result++;
                }
            }
        }

        return result;
    }
}
