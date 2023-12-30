package MethodRefence;

import java.util.ArrayList;
import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("abc",15));
        list.add(new Student("cba",16));
        list.add(new Student("asd",17));
        String[] strings = list.stream().map(Student::getName).toArray(String[]::new);
        System.out.println(Arrays.toString(strings));
    }
}
