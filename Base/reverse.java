package Base;

import java.util.Scanner;

public class reverse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        String result = "";
        for (int length = string.length() - 1; length >= 0; length--) {
            char c = string.charAt(length);
            result = result + c;
        }
        System.out.println(result);
    }
}
