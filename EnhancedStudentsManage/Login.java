package EnhancedStudentsManage;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Login {
    public static boolean login(ArrayList<Student> arrayList) {
        System.out.println("请输入用户名：");
        Scanner scanner = new Scanner(System.in);
        String name;
        name = scanner.next();
        String code = "";
        boolean contain = false;
        for (Student student : arrayList) {
            if (student.getName().equals(name)) {
                code = student.getCode();
                contain = true;
            }
        }

        if (!contain) {
            System.out.println("未能查到用户名：");
            return false;
        }
        System.out.println("请依次输入密码和验证码：");
        String code_in = "";
        String ver = "";
        String ver_in="";
        for (int i = 2; i >= 0; i--) {
            ver = verification();
            System.out.println("验证码为："+ver);
            code_in = scanner.next();
            ver_in=scanner.next();
            if (code_in.equals(code)&&ver.equals(ver_in)) {
                System.out.println("登录成功");
                break;
            }
            System.out.println("密码或者验证码错误，您还剩" + i + "次机会");
        }
        return true;
    }

    public static String verification() {
        Random random = new Random();
        int k = 91;
        char[] code = new char[4];
        for (int i = 0; i < 4; i++) {
//            while(k>=91&&k<=96){           //会产生四个同样的数？？？
//                k=n.nextInt(58)+65;
//            }
            do {
                k = random.nextInt(58) + 65;     //这个运行正常！！！
            } while (k >= 91 && k <= 96);
            code[i] = (char) k;
        }
        int last = random.nextInt(10);
        String str = "";
        int pos = random.nextInt(5);
        //System.out.println("pos:"+pos);
        k = 0;
        for (int i = 0; i < 5; i++) {
            if (i == pos) {
                str = str + last;
            } else {
                str = str + code[k];
                k++;
            }
        }
        return str;
    }
}
