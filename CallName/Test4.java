package CallName;

import java.util.*;

public class Test4 {
    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
        ArrayList<String> city1 =new ArrayList<>();
        ArrayList<String> city2 = new ArrayList<>();
        ArrayList<String> city3 = new ArrayList<>();
        Collections.addAll(city1,"A","B","C","D","E");
        Collections.addAll(city2,"a","b","c","d","e");
        Collections.addAll(city3,"1","2","3","4","5");

        hashMap.put("1",city1);
        hashMap.put("2",city2);
        hashMap.put("3",city3);

        Set<Map.Entry<String, ArrayList<String>>> entries = hashMap.entrySet();

        for (Map.Entry<String, ArrayList<String>> entry : entries) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            StringJoiner stringJoiner = new StringJoiner(",","","");
            for (String s : value) {
                stringJoiner.add(s);
            }
            System.out.println(key+" = "+stringJoiner);

        }
    }
}
