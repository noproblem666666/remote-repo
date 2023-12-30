package StudentsManage;

import java.util.ArrayList;
import java.util.Scanner;

public class Manage {
    public static void main(String[] args) {
        ArrayList<Student> arrayList = new ArrayList<>();
        loop:
        while (true) {
            System.out.println("--------------学生管理系统------------");
            System.out.println("1.添加学生");
            System.out.println("2.删除学生");
            System.out.println("3.修改学生");
            System.out.println("4.查询学生");
            System.out.println("5.退出");
            System.out.println("输入你的选择：");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.next();
            switch (str) {
                case "1" -> addStudent(arrayList);
                case "2" -> deleteStudent(arrayList);
                case "3" -> changeStudent(arrayList);
                case "4" -> searchStudent(arrayList);
                case "5" -> {
                    System.out.println("退出");
                    break loop;
                }
                default -> System.out.println("输错了");
            }
        }


    }

    public static void addStudent(ArrayList<Student> list) {
        Scanner scanner =new Scanner(System.in);
        String ID;

        loop:while (true) {
            ID = scanner.next();
            for (Student student : list) {
                if (student.getID().equals(ID)) {
                    System.out.println("ID已存在");
                    continue loop;
                }
            }
            break;
        }
        String name = scanner.next();
        int age = scanner.nextInt();
        String address = scanner.next();
        Student student =new Student(ID,name,age,address);
        list.add(student);

    }

    public static void deleteStudent(ArrayList<Student> list) {
        Scanner scanner =new Scanner(System.in);
        String ID;
        ID= scanner.next();
        for (Student student : list) {                    //增强型for循环
            if (student.getID().equals(ID)) {
                list.remove(student);
                System.out.println("删除成功");
                return;
            }
        }
        System.out.println("删除失败");
    }

    public static void changeStudent(ArrayList<Student> list) {
        Scanner scanner =new Scanner(System.in);
        String ID;
        ID = scanner.next();
        for (Student student : list) {                    //增强型for循环
            if (student.getID().equals(ID)) {
                System.out.println("请输入新的姓名 年龄 地址：");
                student.setName(scanner.next());
                student.setAge(scanner.nextInt());
                student.setAddress(scanner.next());
                System.out.println("修改成功");
                return;
            }
        }
        System.out.println("未能找到ID，修改失败");
    }

    public static void searchStudent(ArrayList<Student> list) {
        if(list.size()==0){
            System.out.println("容器为空");
            return;
        }
        System.out.println("ID\t\t姓名\t年龄\t家庭住址");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getID()+"\t\t"+list.get(i).getName()+"\t"+list.get(i).getAge()+"\t"+list.get(i).getAddress());
        }
    }
}
