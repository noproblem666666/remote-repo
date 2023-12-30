package EnhancedStudentsManage;

import java.util.ArrayList;
import java.util.Scanner;

public class Forget {
    public static boolean forget(ArrayList<Student> arrayList) {
        System.out.println("请输入用户名：");
        Scanner scanner = new Scanner(System.in);
        String name;
        name = scanner.next();
        String ID = "";
        String phone = "";
        int pos = 0;
        int temp = 0;
        boolean contain = false;
        for (Student student : arrayList) {
            if (student.getName().equals(name)) {
                pos = temp;
                ID = student.getID();
                phone = student.getPhone();
                contain = true;
            }
            temp++;
        }

        if (!contain) {
            System.out.println("未能查到用户名：");
            return false;
        }
        System.out.println("请依次输入身份证号和手机号：");
        String ID_in = "";
        String phone_in = "";
        ID_in = scanner.next();
        phone_in = scanner.next();
        if (ID.equals(ID_in) && phone.equals(phone_in)) {
            String newcode = "";
            String newcode_re = "";
            System.out.println("请输入新的密码");
            newcode = scanner.next();
            System.out.println("请再次确认密码");
            newcode_re = scanner.next();
            if (newcode.equals(newcode_re)) {
                arrayList.get(pos).setCode(newcode);
                System.out.println("修改成功");
                return true;
            }
            System.out.println("两次密码不一致，修改失败");
            return false;
        }
        System.out.println("身份证号或者手机号错误，修改失败");
        return false;
    }
}
