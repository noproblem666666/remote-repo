package CallName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
//随机点名
public class Test1 {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,"wqd","fafaf","dqwdqwd","wceca","acewcc");
        Random random = new Random();
        int i = random.nextInt(arrayList.size());
        String str = arrayList.get(i);
        System.out.println(str);

        Collections.shuffle(arrayList);
        System.out.println(arrayList.get(0));
    }
}
