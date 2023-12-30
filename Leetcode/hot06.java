package Leetcode;

public class hot06 {
    public static void main(String[] args) {
        String s = "ab";
        String p = ".*";
        System.out.println(isMatch(s, p));

    }

    public static boolean isMatch(String s, String p) {     //采用动态规划，从右往左看更加简单
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();
        // dp[i][j]:表示s的前i个字符，p的前j个字符是否能够匹配
        boolean[][] dp = new boolean[cs.length + 1][cp.length + 1];     //dp[0][0]肯定为true
        // 初期值
        // s为空，p为空，能匹配上
        dp[0][0] = true;
        // p为空，s不为空，必为false(boolean数组默认值为false，无需处理)

        // s为空，p不为空，由于*可以匹配0个字符，所以有可能为true
        for (int j = 1; j <= cp.length; j++) {
            if (cp[j - 1] == '*') {           //*不可能单独出现，必定在偶数位出现
                dp[0][j] = dp[0][j - 2];
            }
        }

        //接下来开始填格子
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (cs[i - 1] == cp[j - 1] || cp[j - 1] == '.') {   //模式串和文本穿末位能匹配上的情况
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (cp[j - 1] == '*') {       //*不可能单独出现，必定在偶数位出现,所以j至少为2了，这时去查看上一位的情况
                    if (cs[i - 1] == cp[j - 2] || cp[j - 2] == '.') {  //前一位可以匹配的上
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j]; //前者为匹配0次，后者为匹配一次到多次(因为在之前的初始化中，已经使得dp[i-1][j-2]==dp[i-1][j]),只要有一个为true即可
                    }else{    //前一位匹配不上                     因为j的位置可以匹配多次所以j不变，i的位置已经匹配了，所以就等于i-1的位置和j的匹配，至于i-1的位置到底匹配了几次之前已经算过了，因为i是从小到大递增算的
                        dp[i][j]=dp[i][j-2];  //此时只能匹配0次
                    }
                }
            }
        }
        return dp[s.length()][p.length()];  //返回最终结果
    }

    public static boolean isMatch2(String s, String p) {       //需要考虑的情况太复杂
        int i = 0;
        int j = 0;
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                if (j < (p.length() - 1) && p.charAt(j + 1) == '*') {    //  && 在判断前面为错后就不会判断后面的了，所以不会报错
                    if (p.charAt(j) == '.') {                      //还未能体现*的任意个，可以为后面让出来几个
                        return j == (p.length() - 2);
                    } else {
                        while (i < (s.length() - 1) && s.charAt(i + 1) == s.charAt(i)) {
                            i++;
                        }
                    }
                    i++;
                    j += 2;
                } else {
                    i++;
                    j++;
                }

            } else if (j < (p.length() - 1) && p.charAt(j + 1) == '*') {
                j += 2;
            } else {
                return false;
            }
        }
        return i >= s.length() && j >= p.length();
    }
}
