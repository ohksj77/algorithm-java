import java.util.*;

public class Solution {
    
    public String solution(int n, int t, int m, String[] timetable) {
        int[] crewArrivalTimes = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
            crewArrivalTimes[i] = convertToMinutes(timetable[i]);
        }
        Arrays.sort(crewArrivalTimes);
        
        int firstBusTime = convertToMinutes("09:00");
        List<Integer> busArrivalTimes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            busArrivalTimes.add(firstBusTime + i * t);
        }
        
        int crewIndex = 0;
        for (int i = 0; i < n - 1; i++) {
            int busTime = busArrivalTimes.get(i);
            int count = 0;
            
            while (count < m && crewIndex < crewArrivalTimes.length && crewArrivalTimes[crewIndex] <= busTime) {
                crewIndex++;
                count++;
            }
        }
        
        int lastBusTime = busArrivalTimes.get(n - 1);
        int lastBusSeats = m;
        int conArrivalTime;
        
        int lastBusCrews = 0;
        int lastCrewTime = 0;
        
        for (int i = crewIndex; i < crewArrivalTimes.length && lastBusCrews < m && crewArrivalTimes[i] <= lastBusTime; i++) {
            lastCrewTime = crewArrivalTimes[i];
            lastBusCrews++;
        }
        
        if (lastBusCrews < m) {
            conArrivalTime = lastBusTime;
        } else {
            conArrivalTime = lastCrewTime - 1;
        }
        return convertToTimeFormat(conArrivalTime);
    }
    
    private static int convertToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
    
    private static String convertToTimeFormat(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;
        return String.format("%02d:%02d", hours, mins);
    }
}
