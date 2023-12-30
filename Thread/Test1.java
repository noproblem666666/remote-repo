package Thread;

public class Test1 {
    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        MyThread1 t2 = new MyThread1();
        MyThread1 t3 = new MyThread1();
        MyThread1 t4 = new MyThread1();
        MyThread1 t5 = new MyThread1();

        t1.setName("a");
        t2.setName("b");
        t3.setName("c");
        t4.setName("d");
        t5.setName("e");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
