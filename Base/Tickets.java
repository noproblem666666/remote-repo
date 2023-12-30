package Base;

import java.util.Scanner;

public class Tickets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double price = sc.nextDouble();
        int mouth = sc.nextInt();
        byte type = sc.nextByte();
        System.out.println(calculate(price, mouth, type));
    }

    public static double calculate(double price, int mouth, byte type) {
        if (mouth > 12 || mouth < 1 || type < 1 || type > 2)
            return 0;
        if (mouth >= 5 && mouth <= 10) {
            if (type == 1)
                return price * 0.9;
            else return price * 0.85;
        } else {
            if (type == 1)
                return price * 0.7;
            else return price * 0.65;
        }
    }


}
