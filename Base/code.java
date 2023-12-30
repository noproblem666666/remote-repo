package Base;

import java.util.Random;

public class code {
    public static void main(String[] args) {
        Random n = new Random();
        int k = 91;
        char[] code = new char[4];
        for (int i = 0; i < 4; i++) {
//            while(k>=91&&k<=96){           //会产生四个同样的数？？？
//                k=n.nextInt(58)+65;
//            }
            do {
                k = n.nextInt(58) + 65;     //这个运行正常！！！
            } while (k >= 91 && k <= 96);
            code[i] = (char) k;
            System.out.println(code[i]);
        }
        int last = n.nextInt(10);
        String str = "";
        str = str + code[0] + code[1] + code[2] + code[3];
        System.out.println(str + last);
    }
}
