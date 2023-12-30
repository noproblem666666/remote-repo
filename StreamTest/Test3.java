package StreamTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test3 {
    public static void main(String[] args) {
        ArrayList<String> manList =new ArrayList<>();
        ArrayList<String> womanList = new ArrayList<>();
        Collections.addAll(manList,"ABC,24","ACD,25","CCC,23","YTR,28","AGF,22");
        Collections.addAll(womanList,"abc,24","acd,25","ccc,23","ytr,28","agf,22");
        Stream<String> man = manList.stream()
                .filter(s -> s.split(",")[0].length() == 3)
                .limit(2);
        Stream<String> woman = womanList.stream()
                .filter(s -> s.split(",")[0].startsWith("a"))
                .skip(1);
        List<Actor> list = Stream.concat(man, woman).map(s -> new Actor(s.split(",")[0], Integer.parseInt(s.split(",")[1])))
                .toList();
        for (Actor a : list) {
            System.out.println(a.getAge());
            System.out.println(a.getName());
        }
        System.out.println(list);   //直接打印会出地址
    }
}
