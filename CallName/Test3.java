package CallName;

import java.util.ArrayList;
import java.util.Collections;
//被点名之后就不会再点
public class Test3 {
    public static void main(String[] args) {
        ArrayList<String> arrayList1 = new ArrayList<>();
        ArrayList<String> arrayList2 = new ArrayList<>();

        Collections.addAll(arrayList1,"A","B","C","D","E","a","b","c","d","e");
        int time = 5;

        while(time>0){
            System.out.println("=============第"+(6-time)+"次循环===============");
            int count = arrayList1.size();    //arrayList的大小被删除后会变，要先存储下
            for (int i = 0; i < count; i++) {
                Collections.shuffle(arrayList1);
                String name =arrayList1.remove(0);
                arrayList2.add(name);
                System.out.println(name);
            }
            arrayList1.addAll(arrayList2);
            arrayList2.clear();
            time--;
        }


    }
}
