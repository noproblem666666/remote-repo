package StreamTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test1 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,1,2,3,4,5,6,7,8,9,10);
        List<Integer> collect = arrayList.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println(collect);
    }
}
