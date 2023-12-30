package API;

public class ParseInt {
    public static void main(String[] args) {
        String str = "123456789";
        if (!str.matches("[1-9]\\d{0,9}")) {
            System.out.println("Error");
        } else {
            System.out.println("Right");
            int num = 0;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                num = num * 10 + c - '0';     //字符转数字
            }
            System.out.println(num);
        }

    }
}
