package EnhancedStudentsManage;

import java.util.ArrayList;
import java.util.Scanner;

public class Enroll {
    public static boolean enroll(ArrayList<Student> list){
        Scanner scanner =new Scanner(System.in);
        String name;
        System.out.println("请输入用户名：");
        name=scanner.next();
        if(name.length()<3||name.length()>15){
            System.out.println("长度不符合要求");
            return false;
        }
        if(!contains(list,name)){
            System.out.println("已存在用户名");
            return false;
        }
        if(!check_name(name)){
            System.out.println("不符合规范，必须是字母与数字的组合");
            return false;
        }
        System.out.println("请输入密码：");
        String code = scanner.next();
        System.out.println("请再次确认密码：");
        String code_reconfirm = scanner.next();
        if(!code.equals(code_reconfirm)){
            System.out.println("两次密码不一致");
            return false;
        }
        System.out.println("请输入身份证号码：");
        String ID =scanner.next();
        if(!check_ID(ID)){
            System.out.println("身份证号输入错误");
            return false;
        }
        System.out.println("请输入手机号：");
        String phone =scanner.next();
        if(!check_phone(phone)){
            System.out.println("手机号输入错误");
            return false;
        }
        Student student = new Student(name,code,ID,phone);
        list.add(student);
        System.out.println("注册成功！！！");
        return true;
    }

    public static boolean contains(ArrayList<Student> list,String name){
        for (Student student: list) {
            if(student.getName().equals(name)){
                return false;
            }
        }
        return true;
    }
    public static boolean check_name(String name){
        int math =0;
        int letter = 0;
        for(int i=0;i<name.length();i++){
            if (name.charAt(i) >='0' && name.charAt(i) <= '9') {
                math = 1;
            }
            if((name.charAt(i)>='a'&&name.charAt(i)<='z')||(name.charAt(i)>='A'&&name.charAt(i)<'Z')){
                letter = 1;
            }
        }
        return math == 1 && letter == 1;
    }
    public static boolean check_ID(String ID){
        if(ID.length()!=18){
            System.out.println("ID长度不对，必须为18位");
            return false;
        }
        if(ID.charAt(0)=='0'){
            System.out.println("ID第一位不能为0");
            return false;
        }
        for(int i =0;i<ID.length()-1;i++){
            if(ID.charAt(i)<'0'||ID.charAt(i)>'9'){
                System.out.println("前17位必须为数字");
                return false;
            }
        }
        if((ID.charAt(ID.length()-1)<'0'||ID.charAt(ID.length()-1)>'9')&&ID.charAt(ID.length()-1)!='x'&&ID.charAt(ID.length()-1)!='X'){
            System.out.println("最后一位不合法");              //最后一位的索引是   length-1    ！！！！！！
            return false;
        }
        return true;
    }
    public static boolean check_phone(String phone){
        if(phone.length()!=11){
            System.out.println("phone长度不对，必须为11位");
            return false;
        }
        if(phone.charAt(0)=='0'){
            System.out.println("phone第一位不能为0");
            return false;
        }
        for(int i =0;i<phone.length();i++){
            if(phone.charAt(i)<'0'||phone.charAt(i)>'9'){
                System.out.println("必须全部为数字");
                return false;
            }
        }
        return true;
    }
}
