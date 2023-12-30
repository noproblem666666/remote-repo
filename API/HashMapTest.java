package API;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class HashMapTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("111", "222");
        map.put("333", "444");
        map.put("555", "666");

        Set<String> keys = map.keySet();
        for (String key : keys) {
            String value = map.get(key);
            System.out.println(key + "---" + value);
        }
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = map.get(key);
            System.out.println(key + "---" + value);
        }

        keys.forEach(key -> {                  //lambda  遍历
                    String value = map.get(key);
                    System.out.println(key + "---" + value);
                }
        );

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " ---" + value);

        }

        Iterator<Map.Entry<String, String>> iterator1 = entries.iterator();
        while (iterator1.hasNext()) {
            Map.Entry<String, String> next = iterator1.next();
            String key = next.getKey();
            String value = next.getValue();
            System.out.println(key + "---" + value);
        }

        map.forEach((key, value) -> System.out.println(key + "---" + value));



    }
}
