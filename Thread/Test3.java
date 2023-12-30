package Thread;

import java.util.ArrayList;
import java.util.Collections;

public class Test3 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,11,22,33,44,55,66,77,77,88,99,99,99,100);
        MyThread3 thread1 = new MyThread3(arrayList);
        MyThread3 thread2 = new MyThread3(arrayList);
        thread1.setName("a");
        thread2.setName("b");
        thread1.start();
        thread2.start();
    }
}
