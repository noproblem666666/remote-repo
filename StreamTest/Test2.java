package StreamTest;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test2 {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("zhangsan,23");
        arrayList.add("lisi,24");
        arrayList.add("wangwu,25");
//        Map<String, Integer> collect = arrayList.stream().filter(s -> Integer.parseInt(s.split(",")[1]) >= 24).collect(Collectors.toMap(new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s.split(",")[0];
//            }
//        }, new Function<String, Integer>() {
//            @Override
//            public Integer apply(String s) {
//                return Integer.parseInt(s.split(",")[1]);
//            }
//        }));                                         //有更简单的写法
        Map<String, Integer> collect = arrayList.stream()    //这样写不会太长
                .filter(s -> Integer.parseInt(s.split(",")[1]) >= 24)
                .collect(Collectors.toMap(s -> s.split(",")[0],s -> Integer.parseInt(s.split(",")[1])));
        System.out.println(collect);
    }
}
