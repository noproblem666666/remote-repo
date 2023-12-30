package Leetcode;

import java.util.*;
import java.util.stream.Collectors;
//字母异位词分组
public class hot23 {
    public static void main(String[] args) {

    }
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<HashMap<Character,Integer>,List<Integer>> resMap = new HashMap<>();  //存放每个字符对应的结果
        for (int i = 0; i < strs.length; i++) {
            HashMap<Character,Integer> hashMap =new HashMap<>();   //用hashmap来统计出现的字母和次数
            for (int i1 = 0; i1 < strs[i].length(); i1++) {
                if(!hashMap.containsKey(strs[i].charAt(i1))){
                    hashMap.put(strs[i].charAt(i1),1);
                }else{
                    int temp= hashMap.get(strs[i].charAt(i1));
                    hashMap.put(strs[i].charAt(i1),temp+1);
                }
            }
            List<Integer> list;
            if(!resMap.containsKey(hashMap)){           //如果存在相同的hashmap就加入当前坐标
                list = new ArrayList<>();
            }else{
                list = resMap.get(hashMap);
            }
            list.add(i);
            resMap.put(hashMap,list);
        }
        List<List<String>> resList = new ArrayList<>();       //遍历map得到答案
        for (Map.Entry<HashMap<Character, Integer>, List<Integer>> hashMapListEntry : resMap.entrySet()) {
            List<Integer> value = hashMapListEntry.getValue();
            List<String> list = new ArrayList<>();
            for (Integer integer : value) {
                list.add(strs[integer]);
            }
            resList.add(list);
        }
        return resList;
    }

    public List<List<String>> groupAnagrams2(String[] strs) {    //方法2，利用stream流处理
        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(str -> {
                    // 返回 str 排序后的结果。
                    // 按排序后的结果来grouping by，算子类似于 sql 里的 group by。
                    char[] array = str.toCharArray();
                    Arrays.sort(array);
                    return new String(array);
                })).values());
    }
    public List<List<String>> groupAnagrams3(String[] strs) {    //方法3，比起2把groupingBY里的排序换成了计数
        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(str -> {
                    int[] counter = new int[26];
                    for (int i = 0; i < str.length(); i++) {
                        counter[str.charAt(i) - 'a']++;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 26; i++) {
                        // 这里的 if 是可省略的，但是加上 if 以后，生成的 sb 更短，后续 groupingBy 会更快。
                        if (counter[i] != 0) {
                            sb.append((char) ('a' + i));
                            sb.append(counter[i]);        //因为数组类型没有重写 hashcode() 和 equals() 方法,所以用这种形式才能直接比较
                        }
                    }
                    return sb.toString();
                })).values());
    }

}
