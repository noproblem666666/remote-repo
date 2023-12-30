package Leetcode;

import java.util.HashSet;

//无重复字符的最长字串(注意不是子序列，要求连续)
public class rehot03 {
    //滑动窗口
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int max = 0;
        int count = 0;
        //左指针，记录左窗口边界
        int slow = 0;
        //检查重复字符
        HashSet<Character> check = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            if(!check.contains(chars[i])){
                check.add(chars[i]);
                count++;
                max=Math.max(max,count);
            }else{
                while(chars[slow]!=chars[i]){
                    check.remove(chars[slow]);
                    count--;
                    slow++;
                }
                //找到后在往右靠一步，set和count因为是相同的字符，一增一减相当于没动
                slow++;
            }
        }
        return max;
    }
}
