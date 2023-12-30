package CallName;

import java.util.ArrayList;
import java.util.Collections;
//70%概率男生30%概率女生
public class Test2 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,1,1,1,1,1,1,1,0,0,0);

        ArrayList<String> boyList =new ArrayList<>();
        ArrayList<String> grilList = new ArrayList<>();
        Collections.addAll(boyList,"A","B","C","D","E");
        Collections.addAll(grilList,"a","b","c","d","e");
        int time = 100;
        int boytime =0;
        int griltime =0;
        while(time>0){
            Collections.shuffle(arrayList);
            if(arrayList.get(0)==1){
                Collections.shuffle(boyList);
                System.out.println(boyList.get(0));
                boytime++;
            }else{
                Collections.shuffle(grilList);
                System.out.println(grilList.get(0));
                griltime++;
            }
            time--;
        }
        System.out.println("boytime:"+boytime);
        System.out.println("griltime"+griltime);

    }
}
