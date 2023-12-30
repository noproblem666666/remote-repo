package nowcoder.shuangzhizhen;
//反转字符串
public class BM91 {
    //直接用StringBuilder反转返回
    public String solve (String str) {
        // write code here
        return new StringBuilder(str).reverse().toString();
    }

    //用字符数组反转
    public String solve2 (String str) {
        char[] ans = str.toCharArray();
        int len = str.length();
        for(int i = 0 ; i < len ;i++)
        {
            ans[i] = str.charAt(len-1-i);
        }
        return new String(ans);
    }

    //字符数组左右调换反转
    public String solve3 (String str) {
        char[] cstr = str.toCharArray();
        int len = str.length();
        for(int i = 0 ; i < len/2 ;i++)
        {
            char t = cstr[i];
            cstr[i] = cstr[len-1-i];
            cstr[len-1-i]=t;
        }
        return new String(cstr);
    }
}
