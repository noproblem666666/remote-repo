package Base;

import java.util.Scanner;

public class goal {
    public static void main(String[] args) {           //六个评委打分，去掉一个最高分和最低分，最终的平均得分
        Scanner sc = new Scanner(System.in);
        int[] goal = new int[6];
        for (int i = 0; i < 6; i++) {               //也可以把i++去掉，循环体输入正确再i++
//            while(sc.nextInt()<0||sc.nextInt()>100){   //不能这么写，因为每个sc.nextInt()都必须读入一个数才能执行下去sc.nextInt()
//
//            }
//            goal[i]= sc.nextInt();
            int s = sc.nextInt();
            while(s<0||s>100){
                s=sc.nextInt();                     //当输入数据不对时重新读入
            }
            goal[i] = s;
        }
        goal = calculate(goal);
        double end = 0;
        for (int i = 1; i < 5; i++) {
            end += goal[i];
        }
        end = end / 4;
        System.out.println(end);
    }

    public static int[] calculate(int[] goal) {
        int site = 1;
        int temp = 0;
        while (site == 1) {                          //冒泡排序
            site = 0;
            for (int i = 0; i < 5; i++) {
                if (goal[i] > goal[i + 1]) {
                    temp = goal[i];
                    goal[i] = goal[i + 1];
                    goal[i + 1] = temp;
                    site = 1;
                }
            }
        }
        return goal;

    }


}
