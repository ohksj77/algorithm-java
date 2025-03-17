import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Integer> reports = new HashMap<>();
        Map<String, Integer> alerts = new HashMap<>();
        Map<String, String> reporters = new HashMap<>();
        Set<String> processed = new HashSet<>();
        
        for (String r : report) {
            if (processed.contains(r)) {
                continue;
            }
            processed.add(r);
            String[] ids = r.split(" ");
            
            if (reports.containsKey(ids[1])) {
                int num = reports.get(ids[1]);
                reports.put(ids[1], num + 1);
                reporters.put(ids[1], reporters.getOrDefault(ids[1], "") + " " + ids[0]);
                continue;
            }
            reports.put(ids[1], 1);
            reporters.put(ids[1], reporters.getOrDefault(ids[1], "") + " " + ids[0]);
        }
        
        int[] answer = new int[id_list.length];
        
        for (Map.Entry<String, String> r : reporters.entrySet()) {
            String[] reporter = r.getValue().split(" ");
            if (reporter.length - 1 >= k) {
                for (int i = 1; i < reporter.length; i++) {
                    alerts.put(reporter[i], alerts.getOrDefault(reporter[i], 0) + 1);
                }
            }
        }
        for (int i = 0; i < id_list.length; i++) {
            answer[i] = alerts.getOrDefault(id_list[i], 0);
        }
        return answer;
    }
}
