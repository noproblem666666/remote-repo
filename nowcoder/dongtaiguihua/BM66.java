package nowcoder.dongtaiguihua;

import java.util.ArrayList;
import java.util.HashMap;

//最长公共子串（与上题不同的是，本题要求的子串必须是连续的）
public class BM66 {
    //使用哈希表辅助查找
    public String LCS(String str1, String str2) {
        // write code here
        if (str1 == null || str2 == null) {
            return null;
        }
        int max = 0;
        int start = 0;
        //存储字符串的每个字符的位置，考虑有重复字符
        HashMap<Character, ArrayList<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < str2.length(); i++) {
            if (!hashMap.containsKey(str2.charAt(i))) {
                hashMap.put(str2.charAt(i), new ArrayList<>());
            }
            hashMap.get(str2.charAt(i)).add(i);
        }
        for (int i = 0; i < str1.length(); i++) {
            if (hashMap.containsKey(str1.charAt(i))) {
                for (Integer integer : hashMap.get(str1.charAt(i))) {
                    int temp1 = i;
                    int temp2 = integer;
                    int count = 0;
                    //防止数组越界
                    while (temp1 < str1.length() && temp2 < str2.length() && str1.charAt(temp1) == str2.charAt(temp2)) {
                        count++;
                        temp1++;
                        temp2++;
                    }
                    if (max < count) {
                        max = count;
                        start = integer;
                    }
                }
            }
        }
        return str2.substring(start, start + max);
    }


    //动态规划
    public String LCS2(String str1, String str2) {
        int maxLenth = 0;//记录最长公共子串的长度
        //记录最长公共子串最后一个元素在字符串str1中的位置
        int maxLastIndex = 0;
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                //递推公式，两个字符相等的情况
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    //如果遇到了更长的子串，要更新，记录最长子串的长度，
                    //以及最长子串最后一个元素的位置
                    if (dp[i + 1][j + 1] > maxLenth) {
                        maxLenth = dp[i + 1][j+1];
                        maxLastIndex = i;
                    }
                } else {
                    //递推公式，两个字符不相等的情况
                    dp[i + 1][j+1] = 0;
                }
            }
        }
        //最字符串进行截取，substring(a,b)中a和b分别表示截取的开始和结束位置
        //包左不包右
        return str1.substring(maxLastIndex - maxLenth + 1, maxLastIndex + 1);
    }


    //优化空间，只使用一维数组
    public String LCS3(String str1, String str2) {
        int maxLenth = 0;//记录最长公共子串的长度
        //记录最长公共子串最后一个元素在字符串str1中的位置
        int maxLastIndex = 0;
        int[] dp = new int[str2.length() + 1];
        for (int i = 0; i < str1.length(); i++) {
            //注意这里是倒叙
            for (int j = str2.length() - 1; j >= 0; j--) {
                //递推公式，两个字符相等的情况
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[j + 1] = dp[j] + 1;
                    //如果遇到了更长的子串，要更新，记录最长子串的长度，
                    //以及最长子串最后一个元素的位置
                    if (dp[j + 1] > maxLenth) {
                        maxLenth = dp[j + 1];
                        maxLastIndex = i;
                    }
                } else {
                    //递推公式，两个字符不相等的情况
                    dp[j + 1] = 0;
                }
            }
        }
        //最字符串进行截取，substring(a,b)中a和b分别表示截取的开始和结束位置
        return str1.substring(maxLastIndex - maxLenth + 1, maxLastIndex + 1);
    }
}
