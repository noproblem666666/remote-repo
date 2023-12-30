package Leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//单词拆分  (动态规划)
public class hot49 {
    public boolean wordBreak(String s, List<String> wordDict) {      //有问题，前几步的匹配可能使得后面无法匹配
        int start = 0;
        int end = s.length();

        while (start < end) {
            boolean index = false;
            for (String s1 : wordDict) {
                int length = s1.length();
                if (start + length > end) {
                    continue;
                }
                String temp = s.substring(start, start + length);    //截取片段进行比较
                System.out.println(temp);
                if (temp.equals(s1)) {
                    start += length;
                    index = true;
                    break;
                }
            }
            if(!index){
                return false;
            }
        }
        return true;
    }

    public boolean wordBreak2(String s, List<String> wordDict) {   //动态规划
        Set<String> wordDictSet = new HashSet(wordDict);    //哈希表查找的时间复杂度为1
        boolean[] dp = new boolean[s.length() + 1];    //0默认为true，所以数组应该多一位，默认值为false
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {  //只要前面的为真并且后面存在一个匹配，那么这一位也认为为真
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];     //返回的值为整个字符串是否可以匹配
    }



}
