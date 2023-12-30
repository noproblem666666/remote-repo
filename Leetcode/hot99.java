package Leetcode;
//回文子串（状态转移方程）
public class hot99 {
    public int countSubstrings(String s) {    //暴力解法，时间复杂度较差
        if(s==null){
            return 0;
        }
        int count = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {       //找出所有子串
            for (int j = i; j < chars.length; j++) {
                if(check(chars,i,j)){
                    count++;
                }
            }
        }
        return count;
    }
    public boolean check(char[] chars,int i ,int j){    //检查是否是回文子串
        while(i<j){
            if(chars[i]!=chars[j]){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


    public int countSubstrings2(String s) {      //检查是否为回文子串时节省时间
        // 动态规划法
        boolean[][] dp = new boolean[s.length()][s.length()];
        int ans = 0;

        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {   //状态转移方程
                    dp[i][j] = true;
                    ans++;
                }
            }
        }

        return ans;
    }

    public int countSubstrings3(String s) {     //中心扩展法，较为巧妙
        // 中心扩展法
        int ans = 0;
        for (int center = 0; center < 2 * s.length() - 1; center++) {
            // left和right指针和中心点的关系是？
            // 首先是left，有一个很明显的2倍关系的存在，其次是right，可能和left指向同一个（偶数时），也可能往后移动一个（奇数）
            // 大致的关系出来了，可以选择带两个特殊例子进去看看是否满足。
            int left = center / 2;
            int right = left + center % 2;

            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }



}
