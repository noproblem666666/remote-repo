package Base;

import java.util.Scanner;

public class StringTest {
    public static void main(String[] args) {
        String rightID = "maccree";
        String rightpassword = "112233";
        Scanner scanner = new Scanner(System.in);
        for (int i = 2; i >= 0; i--) {
            String ID = scanner.next();
            String password = scanner.next();
            if (ID.equals(rightID) && password.equals(rightpassword)) {
                System.out.println("登录成功");
                return;
            } else {
                System.out.println("密码或账号错误，还剩" + i + "次机会");
            }
        }
    }
}
