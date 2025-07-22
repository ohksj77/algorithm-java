package algorithm.programmers;

import java.util.*;

class RestoringFomulas {

    public String[] solution(String[] expressions) {

        List<Data> datas = new ArrayList<>();
        for (String exp : expressions) {
            datas.add(new Data(exp));
        }

        int limitL = 2;

        for (String exp : expressions) {
            for (int i = 0; i < exp.length(); i++) {
                char c = exp.charAt(i);
                if ('0' <= c && c <= '9') {
                    limitL = Math.max(limitL, c - '0' + 1);
                }
            }
        }

        boolean[] failed = new boolean[10];

        for (Data data : datas) {
            if (data.c.equals("X")) {
                continue;
            }

            for (int i = limitL; i <= 9; i++) {
                if (failed[i]) {
                    continue;
                }

                if (data.calculate(i, true).equals("Wrong")) {
                    failed[i] = true;
                }
            }
        }

        List<String> answers = new ArrayList<>();
        StringBuilder sb;

        for (Data data : datas) {
            if (!data.c.equals("X")) {
                continue;
            }

            String temp = "";
            boolean flag = false;

            sb = new StringBuilder();
            sb.append(data.a).append(data.isAdd ? " + " : " - ").append(data.b).append(" = ");

            for (int i = limitL; i <= 9; i++) {
                if (failed[i]) {
                    continue;
                }

                if (temp.isEmpty()) {
                    temp = data.calculate(i, false);
                } else if (!temp.equals(data.calculate(i, false))) {
                    flag = true;
                    break;
                }
            }

            if (flag || temp.isEmpty()) {
                sb.append("?");
            } else {

                sb.append(temp);
            }
            answers.add(sb.toString());
        }

        String[] answer = new String[answers.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answers.get(i);
        }
        return answer;
    }

    private static class Data {
        private final String a;
        private final String b;
        private final String c;
        private final boolean isAdd;

        public Data(String expression) {
            StringTokenizer st = new StringTokenizer(expression);

            a = st.nextToken();
            isAdd = st.nextToken().equals("+");
            b = st.nextToken();
            st.nextToken();
            c = st.nextToken();
        }

        public String calculate(int radix, boolean check) {
            int aa = Integer.parseInt(a, radix);
            int bb = Integer.parseInt(b, radix);

            if (!isAdd) {
                bb *= -1;
            }

            if (!check || Integer.parseInt(c, radix) == aa + bb) {
                return Integer.toString(aa + bb, radix);
            }
            return "Wrong";
        }
    }
}
