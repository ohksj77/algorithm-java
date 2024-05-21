package algorithm.programmers;

import java.util.HashMap;
import java.util.Map;

class OpenChattingRoom {

    private static final String ENTER_MESSAGE = "%s님이 들어왔습니다.";
    private static final String LEAVE_MESSAGE = "%s님이 나갔습니다.";
    private static final String ENTER = "Enter";
    private static final String LEAVE = "Leave";
    private static final String CHANGE = "Change";

    public String[] solution(String[] record) {
        final Map<String, String> nicknames = new HashMap<>();
        final Map<Integer, String[]> elcs = new HashMap<>();
        final int length = record.length;
        int index = 0;

        for (int i = 0; i < length; i++) {
            final String[] now = record[i].split(" ");
            final String elc = now[0];
            final String uid = now[1];
            if (elc.equals(LEAVE)) {
                elcs.put(index++, new String[] {uid, LEAVE});
                continue;
            }
            final String nickname = now[2];

            if (elc.equals(CHANGE)) {
                nicknames.put(uid, nickname);
                continue;
            }
            nicknames.put(uid, nickname);
            elcs.put(index++, new String[] {uid, ENTER});
        }

        final String[] answer = new String[index];

        for (int i = 0; i < index; i++) {
            final String[] now = elcs.get(i);
            final String uid = now[0];
            final String el = now[1];
            if (el.equals(ENTER)) {
                answer[i] = String.format(ENTER_MESSAGE, nicknames.get(uid));
                continue;
            }
            answer[i] = String.format(LEAVE_MESSAGE, nicknames.get(uid));
        }

        return answer;
    }
}
