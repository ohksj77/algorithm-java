package algorithm.programmers;

import java.util.ArrayList;
import java.util.List;

public class CellMerge {
    private static final List<String> RESULT = new ArrayList<>();
    private static final String EMPTY = "EMPTY";
    private static final int[] PARENTS = new int[2501];
    private static final String[] TABLE = new String[2501];

    public String[] solution(String[] commands) {
        for (int i = 1; i < PARENTS.length; i++) {
            PARENTS[i] = i;
        }

        for (String command : commands) {
            String[] splitCommand = command.split(" ");
            switch (splitCommand[0]) {
                case "UPDATE" -> update(splitCommand);
                case "MERGE" -> merge(splitCommand);
                case "UNMERGE" -> unmerge(splitCommand);
                case "PRINT" -> print(splitCommand);
            }
        }

        return RESULT.toArray(new String[0]);
    }

    private void update(final String[] commands) {
        if (commands.length == 4) {
            updateIndexValue(commands);
        } else if (commands.length == 3) {
            updateValue(commands);
        }
    }

    private void updateIndexValue(final String[] commands) {
        final int r = Integer.parseInt(commands[1]);
        final int c = Integer.parseInt(commands[2]);
        final String value = commands[3];

        final int parent = find(getIndex(r, c));
        TABLE[parent] = value;
    }

    private int getIndex(final int r, final int c) {
        return 50 * (r - 1) + c;
    }

    private void updateValue(final String[] commands) {
        final String value1 = commands[1];
        final String value2 = commands[2];
        for (int i = 1; i < PARENTS.length; i++) {
            if (value1.equals(TABLE[i])) {
                TABLE[i] = value2;
            }
        }
    }

    private void merge(final String[] commands) {
        final int r1 = Integer.parseInt(commands[1]);
        final int c1 = Integer.parseInt(commands[2]);
        final int r2 = Integer.parseInt(commands[3]);
        final int c2 = Integer.parseInt(commands[4]);

        union(getIndex(r1, c1), getIndex(r2, c2));
    }

    private int find(final int x) {
        if (PARENTS[x] != x) {
            PARENTS[x] = find(PARENTS[x]);
        }
        return PARENTS[x];
    }

    private void union(final int a, final int b) {
        final int findA = find(a);
        final int findB = find(b);

        if (findA != findB) {
            TABLE[findA] = getValue(findA, findB);
            TABLE[findB] = null;
            PARENTS[findB] = findA;
        }
    }

    private String getValue(final int a, final int b) {
        final String value1 = TABLE[a];
        final String value2 = TABLE[b];
        if (value1 != null) {
            return value1;
        }
        return value2;
    }

    private void unmerge(final String[] commands) {
        final int r = Integer.parseInt(commands[1]);
        final int c = Integer.parseInt(commands[2]);

        for (int i = 1; i < PARENTS.length; i++) {
            find(i);
        }

        final int parent = PARENTS[getIndex(r, c)];
        final String value = TABLE[parent];
        for (int i = 1; i < PARENTS.length; i++) {
            if (PARENTS[i] == parent) {
                PARENTS[i] = i;
                TABLE[i] = null;
            }
        }
        TABLE[getIndex(r, c)] = value;
    }

    private void print(final String[] commands) {
        final int r = Integer.parseInt(commands[1]);
        final int c = Integer.parseInt(commands[2]);

        final int parent = find(getIndex(r, c));
        final String value = TABLE[parent];
        if (value == null) {
            RESULT.add(EMPTY);
        } else {
            RESULT.add(value);
        }
    }
}
