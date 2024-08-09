package algorithm.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class 배낭에_보석_담기 {

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] firstLine = br.readLine().split(" ");
        final int n = Integer.parseInt(firstLine[0]);
        final int m = Integer.parseInt(firstLine[1]);

        final Jewel[] jewels = new Jewel[n];

        for (int i = 0; i < n; i++) {
            final String[] jewelInfo = br.readLine().split(" ");
            final int weight = Integer.parseInt(jewelInfo[0]);
            final int value = Integer.parseInt(jewelInfo[1]);
            jewels[i] = new Jewel(weight, value);
        }

        Arrays.sort(jewels);

        double maxValue = 0.0;
        int remainingCapacity = m;

        for (int i = 0; i < n; i++) {
            if (remainingCapacity == 0) {
                break;
            }

            if (jewels[i].weight <= remainingCapacity) {
                maxValue += jewels[i].value;
                remainingCapacity -= jewels[i].weight;
            } else {
                maxValue += jewels[i].ratio * remainingCapacity;
                remainingCapacity = 0;
            }
        }

        bw.write(String.format("%.3f", maxValue));
        bw.flush();
    }

    private static class Jewel implements Comparable<Jewel> {
        private final int weight;
        private final int value;
        private final double ratio;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.ratio = (double) value / weight;
        }

        @Override
        public int compareTo(Jewel other) {
            return Double.compare(other.ratio, this.ratio);
        }
    }
}
