package Base;

import java.util.Scanner;

public class roma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        boolean bool = check(str);
        if (!bool) {
            System.out.println("输入非法");
        }
        System.out.println(swift(str));

    }

    public static boolean check(String str) {
        if (str.length() > 9) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < 48 || str.charAt(i) > 57) {     //  即'0'与'9'
                return false;
            }
        }
        return true;
    }
    public static String swift(String str){
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i) - 48) {
                case 0 -> stringBuilder.append(" ");
                case 1 -> stringBuilder.append("一");
                case 2 -> stringBuilder.append("二");
                case 3 -> stringBuilder.append("三");
                case 4 -> stringBuilder.append("四");
                case 5 -> stringBuilder.append("五");
                case 6 -> stringBuilder.append("六");
                case 7 -> stringBuilder.append("七");
                case 8 -> stringBuilder.append("八");
                case 9 -> stringBuilder.append("九");
            }
        }
        return stringBuilder.toString();
    }

}
