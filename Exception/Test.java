package Exception;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GirlFriend girlFriend = new GirlFriend();
        while (true) {
            try {
                System.out.println("输入姓名");
                String name = scanner.nextLine();
                girlFriend.setName(name);
                System.out.println("输入年龄");
                String age1 = scanner.nextLine();
                int age = Integer.parseInt(age1);
                girlFriend.setAge(age);
                break;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        System.out.println(girlFriend);

    }


}
