package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//最小覆盖子串（滑动窗口）
public class hot32 {
    public String minWindow(String s, String t) {
        int slow = 0 , fast = 0;
        HashMap<Character,Integer> hashMap = new HashMap<>();
        List<String> list =new ArrayList<>();

        char[] chart = t.toCharArray();
        int lent =chart.length;
        for (char aChar : chart) {
            hashMap.put(aChar,hashMap.getOrDefault(aChar,0)+1);   //将t进行字符计数
        }

        char[] chars = s.toCharArray();
        int lens = chars.length;
        while(true){
            while(fast<lens && lent>0){               //向左扩大窗口直到满足覆盖t子串的要求
                if(hashMap.containsKey(chars[fast])){         //每次都要把统计减一
                    if(hashMap.get(chars[fast])>0){         //大于0的时候才把计数器减一
                        lent--;
                    }
                    hashMap.put(chars[fast],hashMap.get(chars[fast])-1);
                }

                fast++;
            }

            while(slow<fast&&(!hashMap.containsKey(chars[slow])||hashMap.get(chars[slow])<0)){  //在不破坏要求的前提下向左缩小窗口
                if(hashMap.containsKey(chars[slow])){                        //如果包含就把统计加一
                    hashMap.put(chars[slow],hashMap.get(chars[slow])+1);
                }
                slow++;
            }
            if(lent!=0){        //说明没有满足的窗口
                break;
            }
            list.add(s.substring(slow,fast));

            hashMap.put(chars[slow],hashMap.get(chars[slow])+1);    //开始新一轮的寻找
            slow++;
            lent++;
        }
        String res = null;
        int min = Integer.MAX_VALUE;
        for (String s1 : list) {       //返回最小的字符串
            //System.out.println(s1);
            if(s1.length()<min){
                min = s1.length();
                res = s1;
            }
        }
        if(res==null){
            return "";
        }
        return res;
    }

    public String minWindow2(String s, String t) {     //另一种答案的写法，没有使用map和list
        if (s == null || s.length() == 0 || t == null || t.length() == 0){
            return "";
        }
        int[] need = new int[128];
        //记录需要的字符的个数
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        //l是当前左边界，r是当前右边界，size记录窗口大小，count是需求的字符个数，start是最小覆盖串开始的index
        int l = 0, r = 0, size = Integer.MAX_VALUE, count = t.length(), start = 0;
        //遍历所有字符
        while (r < s.length()) {
            char c = s.charAt(r);
            if (need[c] > 0) {//需要字符c
                count--;
            }
            need[c]--;//把右边的字符加入窗口
            if (count == 0) {//窗口中已经包含所有字符
                while (l < r && need[s.charAt(l)] < 0) {
                    need[s.charAt(l)]++;//释放右边移动出窗口的字符
                    l++;//指针右移
                }
                if (r - l + 1 < size) {//不能右移时候挑战最小窗口大小，更新最小窗口开始的start
                    size = r - l + 1;
                    start = l;//记录下最小值时候的开始位置，最后返回覆盖串时候会用到
                }
                //l向右移动后窗口肯定不能满足了 重新开始循环
                need[s.charAt(l)]++;
                l++;
                count++;
            }
            r++;
        }
        return size == Integer.MAX_VALUE ? "" : s.substring(start, start + size);
    }

}
