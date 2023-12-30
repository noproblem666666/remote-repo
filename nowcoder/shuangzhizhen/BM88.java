package nowcoder.shuangzhizhen;
//判断是否为回文字符串
public class BM88 {
    public boolean judge (String str) {
        // write code here
        if(str==null||str.length()==0){
            return true;
        }
        int i = 0;
        int j = str.length()-1;
        char[] chars = str.toCharArray();
        while(i<j){
            if(chars[i]!=chars[j]){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    //用StringBuffer反转字符串在比较是否相等
  public boolean judge2 (String str) {
        StringBuffer temp = new StringBuffer(str);
        //反转字符串
        String s = temp.reverse().toString();
        //比较字符串是否相等
        if(s.equals(str))
            return true;
        return false;
    }
}
