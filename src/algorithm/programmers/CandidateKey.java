package algorithm.programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class CandidateKey {

    public static void main(String[] args) {
        CandidateKey candidateKey = new CandidateKey();

        System.out.println(candidateKey.solution(
                new String[][]{{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"},
                        {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"},
                        {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}}));

        System.out.println(candidateKey.solution(
                new String[][]{{"100","100","ryan","music","2"}, {"200","200","apeach","math","2"},
                        {"300","300","tube","computer","3"}, {"400","400","con","computer","4"},
                        {"500","500","muzi","music","3"}, {"600","600","apeach","music","2"}}));

        System.out.println(candidateKey.solution(
                new String[][]{ {"a","1","aaa","c","ng"},
                        {"a","1","bbb","e","g"},
                        {"c","1","aaa","d","ng"},
                        {"d","2","bbb","d","ng"}}
        ));
    }

    public int solution(String[][] relation) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(IntStream.range(0, relation[0].length).toArray(), 0, new ArrayList<>(), result);

        List<List<Integer>> answerIndexes = new ArrayList<>();

        for (List<Integer> now : result) {
            List<String> values = new ArrayList<>();
            for (int j = 0; j < relation.length; j++) {
                String[] nowRelation = relation[j];
                StringBuilder sb = new StringBuilder();
                for (Integer index : now) {
                    sb.append(nowRelation[index]);
                }
                values.add(sb.toString());
            }
            if (values.size() == values.stream().distinct().count()) {
                boolean flag = true;
                List<List<Integer>> pop = new ArrayList<>();

                for (List<Integer> answerIndex : answerIndexes) {
                    if (new HashSet<>(now).containsAll(answerIndex)) {
                        flag = false;
                    }
                    if (new HashSet<>(answerIndex).containsAll(now)) {
                        pop.add(answerIndex);
                        flag = false;
                    }
                }
                if (!pop.isEmpty()) {
                    answerIndexes.add(now);
                    for (List<Integer> p : pop) {
                        answerIndexes.remove(p);
                    }
                }

                if (flag) {
                    answerIndexes.add(now);
                }
            }
        }

        return answerIndexes.size();
    }

    private void backtrack(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        if (!path.isEmpty()) {
            result.add(new ArrayList<>(path));
        }
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(nums, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
}
