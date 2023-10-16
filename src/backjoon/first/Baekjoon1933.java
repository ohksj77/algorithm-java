package backjoon.first;

import java.io.*;
import java.util.*;

public class Baekjoon1933 {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        List<Building> buildings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            buildings.add(new Building(l, h));
            buildings.add(new Building(r, -h));
        }
        buildings.sort(new Comparator<Building>() {
            @Override
            public int compare(Building o1, Building o2) {
                if (o1.x == o2.x) {
                    return o2.h - o1.h;
                }
                return o1.x - o2.x;
            }
        });

        TreeMap<Integer, Integer> tree = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        List<Building> answer = new ArrayList<>();
        for (int i = 0; i < buildings.size(); i++) {
            Building building = buildings.get(i);
            if (building.h > 0) {
                tree.put(building.h, tree.getOrDefault(building.h, 0) + 1);
            } else {
                int key = -building.h;
                int val = tree.get(key);
                if (val == 1) {
                    tree.remove(-building.h);
                } else {
                    tree.put(key, val - 1);
                }
            }
            if (tree.size() == 0) {
                answer.add(new Building(building.x, 0));
                continue;
            }
            answer.add(new Building(building.x, tree.firstKey()));
        }
        bw.write(answer.get(0).x + " " + answer.get(0).h + " ");
        int previous = answer.get(0).h;
        for (int i = 0; i < answer.size(); i++) {
            if (previous != answer.get(i).h) {
                bw.write(answer.get(i).x + " " + answer.get(i).h + " ");
                previous = answer.get(i).h;
            }
        }
        bw.flush();
    }

    private static class Building {
        int x;
        int h;

        public Building(int x, int h) {
            this.x = x;
            this.h = h;
        }
    }
}
