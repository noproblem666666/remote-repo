package Leetcode;

import java.util.*;

//字母异位词分组
public class rehot23 {
    //使用hashmap统计单词中字母出现的次数，每次进行比较，时间复杂度和空间复杂度都较差
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<HashMap<Character,Integer>,List<String>> hashMap = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            HashMap<Character,Integer> map = new HashMap<>();
            for (char aChar : chars) {
                map.put(aChar,map.getOrDefault(aChar,0)+1);
            }
            if (!hashMap.containsKey(map)) {
                List<String> add = new ArrayList<>();
                add.add(s);
                hashMap.put(map, add);
            } else {
                hashMap.get(map).add(s);
            }
        }
        for (HashMap<Character,Integer> characters : hashMap.keySet()) {
            res.add(hashMap.get(characters));
        }
        return res;
    }

    //既然含有的字母相同，排序之后肯定相同，就不需要再统计出现次数了
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            //直接排序
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    //使用数组进行计数，相较于hashmap速度更快
    public List<List<String>> groupAnagrams3(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }



}
