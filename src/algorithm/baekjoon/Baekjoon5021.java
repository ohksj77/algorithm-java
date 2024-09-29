package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Baekjoon5021 {
    private static final Map<String, Double> BLOODS = new HashMap<>();
    private static final Map<String, List<String>> FAMILIES = new HashMap<>();

    public static void main(String[] args) throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final String[] nm = br.readLine().split(" ");
        final int n = Integer.parseInt(nm[0]);
        final int m = Integer.parseInt(nm[1]);

        final String king = br.readLine();

        for (int i = 0; i < n; i++) {
            final String[] input = br.readLine().split(" ");
            final String son = input[0];

            final List<String> parents = FAMILIES.getOrDefault(son, new ArrayList<>());
            final String father = input[1];
            parents.add(father);
            final String mother = input[2];
            parents.add(mother);
            FAMILIES.put(son, parents);

            BLOODS.put(son, -1D);
            BLOODS.put(father, -1D);
            BLOODS.put(mother, -1D);
        }
        BLOODS.put(king, 1D);

        dfsAll();

        String successor = br.readLine();
        for (int i = 1; i < m; i++) {
            final String competitor = br.readLine();
            if (BLOODS.getOrDefault(competitor, 0D) > BLOODS.getOrDefault(successor, 0D)) {
                successor = competitor;
            }
        }

        bw.write(successor);
        bw.flush();
    }

    private static void dfsAll() {
        for (final String son : FAMILIES.keySet()) {
            dfs(son);
        }
    }

    private static double dfs(final String son) {
        final Double sonBlood = BLOODS.get(son);
        if (sonBlood != -1D) {
            return sonBlood;
        }
        if (!FAMILIES.containsKey(son)) {
            BLOODS.put(son, 0D);
            return 0D;
        }
        final List<String> parents = FAMILIES.get(son);
        final double fatherBlood = dfs(parents.get(0));
        final double motherBlood = dfs(parents.get(1));
        BLOODS.put(son, (fatherBlood + motherBlood) / 2);

        return BLOODS.get(son);
    }
}
