package Base;

import java.util.Scanner;

public class circle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.next();
        String b = scanner.next();
        if(a.length()!=b.length()){
            System.out.println("NO!!!");
            return;
        }
        char temp = a.charAt(0);
        int pos = 0;
        for(int i=0;i<b.length();i++){
            if(b.charAt(i)==temp)
                pos = i;                 //如果有两个相同的字符的话，可能判断错误！！！只会取最后一个相同的字符的位置  如 abcabd 和 abcabd 判断错误
        }
        System.out.println(pos);
        boolean inf = true;
        for(int i=0;i<b.length();i++){
            if(a.charAt(i)!=b.charAt((pos+i)%b.length())){    //可以再加一层循环考虑所有情况
                inf = false;
            }
        }
        if(inf){
            System.out.println("YES!!!");        //也可以用截取和拼接进行循环
        }else{
            System.out.println("NO!!!");
        }

    }
}
