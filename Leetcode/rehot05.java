package Leetcode;

//最长回文字串 Todo：要注意检查的地方还有很多
public class rehot05 {
    //找中心点，考虑奇数长度和偶数长度两种情况
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int max = 0;
        String res = "";
        for (int i = 0; i < chars.length; i++) {
            int count = 0;
            //检查防止越界
            if (i < chars.length - 1 && chars[i] == chars[i + 1]) {
                count += 2;
                //这里也要检查一次，防止一次都没进入循环而未能更新res
                if (max < count) {
                    max = count;
                    //注意substring包左不包右 ！！！
                    res = s.substring(i, i + 1 + 1);
                }
                int j = 1;
                while (i - j >= 0 && i + 1 + j < chars.length) {
                    if (chars[i - j] == chars[i + 1 + j]) {
                        count += 2;
                        if (max < count) {
                            max = count;
                            res = s.substring(i - j, i + 1 + j + 1);
                        }
                    } else {
                        break;
                    }
                    j++;
                }
            }
            count = 1;
            //这里也要检查一次，防止一次都没进入循环而未能更新res
            if (max < count) {
                max = count;
                res = s.substring(i, i + 1);
            }
            int j = 1;
            while (i - j >= 0 && i + j < chars.length) {
                if (chars[i - j] == chars[i + j]) {
                    count += 2;
                    if (max < count) {
                        max = count;
                        res = s.substring(i - j, i + j + 1);
                    }
                } else {
                    break;
                }
                j++;
            }

        }
        return res;
    }

    //动态规划
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

}
