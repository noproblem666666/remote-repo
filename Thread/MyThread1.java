package Thread;

import java.util.Random;

public class MyThread1 extends Thread{
    //共享数据：红包金额和数量
    static double money = 100;   //静态数据使得多个线程共享数据
    static int count = 3;
    static final double min = 0.01;//最小中奖金额

    @Override
    public void run() {
        //套路：1、循环 2、同步代码块 3、判断共享数据是否已经到末尾（已到末尾） 4、判断共享数据是否已经到末尾（没有到末尾）
        double prize = 0;
        synchronized (MyThread1.class){
            if(count==0){
                System.out.println(getName()+"没有抢到红包！");
            }else{
                if(count==1){
                    prize = money;
                }else{
                    Random random = new Random();
                    double max = money-(count-1)*min;
                    prize = random.nextDouble(max);
                    if(prize<min){
                        prize=min;
                    }

                }
                count--;
                money-=prize;
                System.out.println(getName()+"抢到了"+ prize +"元的红包!");
            }
        }
    }
}
