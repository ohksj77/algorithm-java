package backjoon.first;

import java.io.*;
import java.util.*;

public class Baekjoon2261 {

    private static Element[] elements;

    private static Comparator<Element> yCompare = new Comparator<>() {
        @Override
        public int compare(Element o1, Element o2) {
            return o1.y - o2.y;
        }
    };

    private static Comparator<Element> xCompare = new Comparator<>() {
        @Override
        public int compare(Element o1, Element o2) {
            return o1.x - o2.x;
        }
    };

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        elements = new Element[n];

        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            elements[i] = new Element(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(elements, xCompare);

        bw.write(String.valueOf(closest(0, n - 1)));
        bw.flush();
    }

    private static int closest(int start, int end) {
        if (end - start + 1 < 4) {
            return brute(start, end);
        }
        int mid = (start + end) / 2;

        int left = closest(start, mid);
        int right = closest(mid + 1, end);

        int minDist = Math.min(left, right);

        int band = middleBand(start, mid, end, minDist);
        return Math.min(minDist, band);
    }

    private static int brute(int start, int end) {

        int minDist = Integer.MAX_VALUE;

        for (int i = start; i < end; i++) {
            Element x0 = elements[i];
            for (int j = i + 1; j <= end; j++) {
                minDist = Math.min(minDist, dist(x0, elements[j]));
            }
        }
        return minDist;
    }

    private static int middleBand(int start, int mid, int end, int minDist) {
        int xDist;
        ArrayList<Element> list = new ArrayList<>();

        int midX = elements[mid].x;
        for (int i = start; i <= end; i++) {
            xDist = elements[i].x - midX;
            if (xDist * xDist < minDist) {
                list.add(elements[i]);
            }
        }

        Collections.sort(list, yCompare);

        int yDist;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                yDist = list.get(i).y - list.get(j).y;
                if (yDist * yDist < minDist) {
                    minDist = Math.min(dist(list.get(i), list.get(j)), minDist);
                } else {
                    break;
                }
            }
        }
        return minDist;
    }

    private static int dist(Element e1, Element e2) {
        return (e1.x - e2.x) * (e1.x - e2.x) + (e1.y - e2.y) * (e1.y - e2.y);
    }

    private static class Element {
        int x;
        int y;

        public Element(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
