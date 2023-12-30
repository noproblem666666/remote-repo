package nowcoder.zifuchuan;
//最长公共前缀
public class BM84 {
    public String longestCommonPrefix (String[] strs) {
        // write code here
        if(strs.length==0){
            //题目要求返回空字符串而不是null
            return "";
        }
        String temp = strs[0];
        //最长公共前缀要取的是所有公共前缀中的最小值
        int min = temp.length();//初值取最大值
        for (int i = 1; i < strs.length; i++) {
            //既可以用来计数，也可以用来记录位置
            int count = 0;
            //先判断是否越界
            while(count<temp.length()&&count<strs[i].length()&&temp.charAt(count)==strs[i].charAt(count)){
                count++;
            }
            if(count<min){
                min = count;
            }
        }
        return temp.substring(0,min);
    }

    //纵向扫描比较
    public String longestCommonPrefix2 (String[] strs) {
        // //纵向扫描
        if(strs.length==0 || strs==null){
            return "";
        }

        int rows = strs.length;
        int cols = strs[0].length();
        //开始扫描
        for(int i=0;i<cols;i++){
            char firstChar = strs[0].charAt(i);
            for(int j=1;j<rows;j++){
                if(strs[j].length()==i || strs[j].charAt(i)!=firstChar){
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    }

    //横向比较，不断用两个字符串的公共前缀和后一个字符串相比较
    public String longestCommonPrefix3 (String[] strs) {
        // 横向扫描
        if(strs.length==0 || strs==null){
            return "";
        }
        //以第一个字符开始
        String prefix = strs[0];
        for(int i=1;i<strs.length;i++){
            prefix = GetCommonPrefix(prefix,strs[i]);
            //表示最长公共前缀就是""
            if(prefix.length()==0){
                return "";
            }
        }
        return prefix;
    }
    //返回两子串公共前缀函数
    public String GetCommonPrefix(String s1,String s2){
        int len= Math.min(s1.length(),s2.length());
        int flag=0;
        while(flag<len && s1.charAt(flag)==s2.charAt(flag)){
            flag++;
        }
        return s1.substring(0,flag);
    }
}
