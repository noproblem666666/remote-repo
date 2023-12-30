package API;

import java.util.ArrayList;
import java.util.Scanner;

public class Add {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Scanner scanner =new Scanner(System.in);

        while(true){
            String input = scanner.nextLine();
            int num =Integer.parseInt(input);
            if(num<1||num>100){
                System.out.println("Error!");
                continue;
            }
            arrayList.add(num);
            boolean index = check(arrayList);
            if(index){
                break;
            }
        }
        int sum = 0;
        for (int num : arrayList) {     //简便写法
            sum += num;
        }
        System.out.println(sum);
    }

    private static boolean check(ArrayList<Integer> arrayList) {
        int sum = 0;
        for (int num : arrayList) {     //简便写法
            sum += num;
        }
        return sum > 200;

    }
}
