package algorithm.study.fifth;

import java.util.Collections;
import java.util.LinkedList;

public class MuziMukbangLive {

    public static void main(String[] args) {
        int answer = solution(new int[]{3, 1, 2}, 5);
        System.out.println(answer);
    }

    public static int solution(int[] food_times, long k) {
        LinkedList<Food> list = new LinkedList<>();
        int len = food_times.length;
        for (int i = 0; i < len; i++) {
            list.add(new Food(i + 1, food_times[i]));
        }
        Collections.sort(list, (o1, o2) -> o1.time - o2.time);

        int current_time = 0;
        int idx = 0;
        for (Food f : list) {
            long diff = f.time - current_time;
            if (diff != 0) {
                long spend = diff * len;
                if (spend <= k) {
                    k -= spend;
                    current_time = f.time;
                } else {
                    k %= len;
                    list.subList(idx, food_times.length).sort((o1, o2) -> o1.num - o2.num);
                    return list.get(idx + (int) k).num;
                }
            }
            idx++;
            len--;
        }
        return -1;
    }

    static class Food {
        int num;
        int time;

        Food(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
}
