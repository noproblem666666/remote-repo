package nowcoder.dongtaiguihua;

//最长回文子串
public class BM73 {
    //暴力法，中心扩展法，从一个位置向左右两边不断比较（考虑奇数长度和偶数长度）时间复杂度o(n*n).空间复杂度o(1)
    public int getLongestPalindrome(String A) {
        // write code here
        if (A == null || A.equals("")) {
            return 0;
        }
        if (A.length() == 1) {
            return 1;
        }
        char[] chars = A.toCharArray();
        int max = 1;
        for (int i = 0; i < chars.length - 1; i++) {
            int count = 0;
            int j = 0;
            //长度为偶数的情况
            while (i - j >= 0 && i + j + 1 < chars.length && chars[i - j] == chars[i + j + 1]) {
                count += 2;
                j++;
            }
            if (count > max) {
                max = count;
            }
            //长度为奇数的情况
            count = 1;
            j = 0;
            while (i - j - 1 >= 0 && i + j + 1 < chars.length && chars[i - j - 1] == chars[i + j + 1]) {
                count += 2;
                j++;
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }


    //动态规划的方法，用二维辅助数组
    public int getLongestPalindrome2(String A) {
        // write code here
        if (A == null || A.equals("")) {
            return 0;
        }
        if (A.length() == 1) {
            return 1;
        }
        int n = A.length();
        char[] chars = A.toCharArray();
        int max = 0;
        boolean[][] dp = new boolean[n][n];
        //Todo:这种循环方法是错误的，应该按照间隔差距循环，否则先确定左右边界时会有中间的值还未更新
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = i; j < dp.length; j++) {
//                if(chars[i] == chars[j]){
//                    if(j-i<3){
//                        dp[i][j] = true;
//                    }else{
//                        dp[i][j] = dp[i+1][j-1];
//                    }
//                    if(dp[i][j]){
//                        max = Math.max(max,j-i+1);
//                    }
//                }
//            }
//        }
        for (int len = 0; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                if (chars[i] == chars[j]) {
                    if (len <= 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    if (dp[i][j]) {
                        max = Math.max(max, len + 1);
                    }
                }
            }
        }
        return max;
    }
}
