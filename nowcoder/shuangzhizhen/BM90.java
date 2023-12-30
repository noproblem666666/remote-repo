package nowcoder.shuangzhizhen;

import java.util.HashMap;

//最小覆盖字串（滑动窗口）
public class BM90 {
    //Todo:记住代码怎么写
    //检查是否有小于0的
    boolean check(int[] hash) {
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] < 0)
                return false;
        }
        return true;
    };

    public String minWindow (String S, String T) {
        int cnt = S.length() + 1;
        //记录目标字符串T的字符个数
        int[] hash = new int[128];
        for(int i = 0; i < T.length(); i++)
            //初始化哈希表都为负数，找的时候再加为正
            hash[T.charAt(i)] -= 1;
        int slow = 0, fast = 0;
        //记录左右区间
        int left = -1, right = -1;
        //Todo：滑动窗口代码模板
        for(; fast < S.length(); fast++){
            char c = S.charAt(fast);
            //目标字符匹配+1
            hash[c]++;
            //没有小于0的说明都覆盖了，缩小窗口
            while(check(hash)){
                //取最优解
                if(cnt > fast - slow + 1){
                    cnt = fast - slow + 1;
                    left = slow;
                    right = fast;
                }
                c = S.charAt(slow);
                //缩小窗口的时候减1
                hash[c]--;
                //窗口缩小
                slow++;
            }
        }
        //找不到的情况
        if(left == -1)
            return "";
        return S.substring(left, right + 1);
    }
}
