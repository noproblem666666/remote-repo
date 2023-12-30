package Base;

public class Prime {
    public static void main(String[] args) {

        int n = 0;
        int count = 0;
        for (int i = 101; i < 201; i++) {
            boolean flag = true;              //！！！要放进循环体，每次初始化都为true
            for (int j = 2; j * j <= i; j++) {    //！！！要用<=，因为有些121 ，169等平方数情况会被跳过当作质数
                if (i % j == 0) {
                    flag = false;
                    break;               //break只会跳出一层循环
                }
                n++;
            }
            if (flag){              //flag == true 可以直接写为 flag
                count++;
                System.out.println(i + "是一个质数");
            }

        }

        System.out.println(n);
        System.out.println(count);
    }
}
