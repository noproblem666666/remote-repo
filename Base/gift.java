package Base;

import java.util.Random;

public class gift {
    public static void main(String[] args) {
        int[] gift = {2, 588, 888, 1000, 10000};
        int[] tip = new int[5];
        for (int i = 0; i < 5; i++) {
            tip[i] = 1;
        }
        Random random = new Random();
        for (int i = 0; i < 5; i++) {                 //也可以只在序号不重复的时候i++
            int ra = random.nextInt(5);
            while (tip[ra] == 0) {
                ra = random.nextInt(5);
            }
            System.out.println(gift[ra]);              //更简单的方法是打乱数组然后直接输出
            tip[ra] = 0;
        }

    }
}
