package algorithm.programmers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class Tuple {

    public static void main(String[] args) throws Exception {
        final Tuple tuple = new Tuple();
        System.out.println(tuple.solution("{{1,2,3},{2,1},{1,2,4,3},{2}}"));
    }

    public Set<Integer> solution(String s) {
        final Set<Integer> answer = new LinkedHashSet<>();
        final List<int[]> all = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == ',') {
                continue;
            }

            if (c == '}') {
                if (list.isEmpty()) {
                    continue;
                }
                all.add(list.stream().mapToInt(Integer::intValue).toArray());
                list = new ArrayList<>();
                continue;
            }

            final StringBuilder sb = new StringBuilder();
            while ('0' <= c && c <= '9') {
                sb.append(c);
                c = s.charAt(++i);
            }
            i -= 1;
            list.add(Integer.parseInt(sb.toString()));
        }

        all.sort(Comparator.comparingInt(o -> o.length));

        for (int[] arr : all) {
            for (int i : arr) {
                answer.add(i);
            }
        }

        return answer;
    }
}
