package EnhancedStudentsManage;

import java.util.ArrayList;
import java.util.Scanner;

public class Manage {
    public static void main(String[] args) {
        ArrayList<Student> arrayList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------增强学生管理系统-----------------");
        System.out.println("1.注册");
        System.out.println("2.登录");
        System.out.println("3.忘记密码");
        System.out.println("4.退出");
        String str;
        loop:
        while (true) {
            str = scanner.next();
            switch (str) {
                case "1" -> {
                    boolean bool = Enroll.enroll(arrayList);
                }
                case "2" -> {
                    boolean bool2 = Login.login(arrayList);
                }
                case "3" -> {
                    boolean bool3 = Forget.forget(arrayList);
                }
                case "4" -> {
                    break loop;
                }
                default -> System.out.println("输错了");
            }
        }
    }
}
