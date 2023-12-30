package Thread;

import java.util.ArrayList;
import java.util.Collections;

public class Test2 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,11,22,33,44,55,66,77,77,88,99,99,99,100);
        MyThread2 thread1 = new MyThread2(arrayList);
        MyThread2 thread2 = new MyThread2(arrayList);
        thread1.setName("a");
        thread2.setName("b");
        thread1.start();
        thread2.start();
    }
}
