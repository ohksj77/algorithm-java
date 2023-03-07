package backjoon.first;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon10825 {

    private static Student[] students;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        students = new Student[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            students[i] = new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        List<Student> result = Arrays.asList(students);
        result
                .sort(new Comparator<>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        if (o1.language < o2.language) {
                            return 1;
                        } else if (o1.language == o2.language) {
                            if (o1.english > o2.english) {
                                return 1;
                            } else if (o1.english == o2.english) {
                                if (o1.math < o2.math) {
                                    return 1;
                                } else if (o1.math == o2.math) {
                                    return o1.name.compareTo(o2.name);
                                }
                            }
                        }
                        return -1;
                    }
                });

        for (Student student : result) {
            bw.write(student.name + "\n");
        }
        bw.flush();
    }

    private static class Student {
        String name;
        int language;
        int english;
        int math;

        public Student(String name, int language, int english, int math) {
            this.name = name;
            this.language = language;
            this.english = english;
            this.math = math;
        }
    }
}
