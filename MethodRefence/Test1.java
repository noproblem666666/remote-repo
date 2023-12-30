package MethodRefence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Test1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"a,15","b,16","c,17","d,18","e,19");
        Student[] students = list.stream().map(Student::new).toArray(Student[]::new);
        System.out.println(Arrays.toString(students));
    }
}
