package Leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//最小覆盖子串（滑动窗口）
public class rehot32 {
    public String minWindow(String s, String t){

        char[] charsT = t.toCharArray();
        char[] charsS = s.toCharArray();
        String res = "";
        int min = Integer.MAX_VALUE;
        int m = t.length();
        //存储t中需要的各个英文字母
        int[] count = new int[100];
        for (char c : charsT) {
            //将对应位置的数组减一
            count[c-'A'] -= 1;
        }
        //滑动窗口，左指针，右指针
        int left = 0;
        int right = 0;
        while(right<charsS.length){
            count[charsS[right]-'A']++;
            //说明加之前是小于0的，不可能是左窗口移动导致的，因为减少之前必定加过
            if(count[charsS[right]-'A']<=0){
                m--;
            }
            //right已经提前移动过了
            right++;
            while(m==0){
                if(right-left<min){
                    min = right-left;
                    //包左不包右,right已经提前移动过了
                    res = s.substring(left,right);
                }
                count[charsS[left]-'A']--;
                //如果不是t中的字母，那么它之前肯定加过，不可能小于0
                if(count[charsS[left]-'A']<0){
                    m++;
                }
                left++;
            }
        }
        return res;
    }


    //使用hashmap的解法（其实只用一个就行了，每次添加和删除时像上面一样检查一下数字）
    Map<Character, Integer> ori = new HashMap<Character, Integer>();
    Map<Character, Integer> cnt = new HashMap<Character, Integer>();

    public String minWindow2(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public boolean check() {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }


}
