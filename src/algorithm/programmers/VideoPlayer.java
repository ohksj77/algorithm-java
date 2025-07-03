package algorithm.programmers;

import java.time.*;

class VideoPlayer {
    public String solution(
            String video_len, String pos, String op_start, String op_end, String[] commands) {
        LocalTime start = LocalTime.of(0, 0, 0);
        LocalTime length = toLocalTime(video_len);
        LocalTime position = toLocalTime(pos);
        LocalTime opStart = toLocalTime(op_start);
        LocalTime opEnd = toLocalTime(op_end);

        for (String command : commands) {
            if (position.equals(opStart) || position.isAfter(opStart) && position.isBefore(opEnd)) {
                position = opEnd;
            }
            if (command.equals("next")) {
                position = position.plusSeconds(10);
                if (position.isAfter(length)) {
                    position = length;
                }
            } else if (command.equals("prev")) {
                position = position.minusSeconds(10);
                if (position.isBefore(start) || position.isAfter(length)) {
                    position = start;
                }
            }
            if (position.equals(opStart) || position.isAfter(opStart) && position.isBefore(opEnd)) {
                position = opEnd;
            }
        }
        int hour = position.getHour();
        int minute = position.getMinute();
        minute += hour * 60;
        int second = position.getSecond();

        StringBuilder sb = new StringBuilder();

        if (minute < 10) {
            sb.append("0");
        }
        sb.append(minute).append(":");
        if (second < 10) {
            sb.append("0");
        }
        sb.append(second);

        return sb.toString();
    }

    private LocalTime toLocalTime(String time) {
        String[] values = time.split(":");
        return LocalTime.of(0, Integer.parseInt(values[0]), Integer.parseInt(values[1]));
    }
}
