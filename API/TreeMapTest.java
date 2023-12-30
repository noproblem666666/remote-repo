package API;

import java.util.TreeMap;
import java.util.function.BiConsumer;

public class TreeMapTest {
    public static void main(String[] args) {
        String str = "adenjvnvjebdsvnadsajadnjdhabvevqwvea";
        TreeMap<Character,Integer> treeMap = new TreeMap<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(treeMap.containsKey(c)){
                int temp = treeMap.get(c);
                treeMap.put(c,temp+1);
            }else{
                treeMap.put(c,1);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();

        treeMap.forEach(new BiConsumer<Character, Integer>() {
            @Override
            public void accept(Character character, Integer integer) {
                stringBuilder.append(character).append("(").append(integer).append(")");
            }
        });
        System.out.println(stringBuilder);
    }
}
