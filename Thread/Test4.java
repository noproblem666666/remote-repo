package Thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,11,22,33,44,55,66,77,77,88,99,99,99,100);
        MyCallable myCallable =new MyCallable(arrayList);
        FutureTask<Integer> future1 =new FutureTask<>(myCallable);
        FutureTask<Integer> future2 = new FutureTask<>(myCallable);
        Thread t1 =new Thread(future1);
        Thread t2 = new Thread(future2);
        t1.setName("a");
        t2.setName("b");
        t1.start();
        t2.start();
        Integer integer1 = future1.get();
        Integer integer2 = future2.get();
        System.out.println(integer1);
        System.out.println(integer2);
    }
}