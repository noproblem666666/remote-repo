package nowcoder.zifuchuan;

import java.util.Arrays;
import java.util.Stack;

//字符串变形
public class BM83 {
    //以空格为基础进行切割，以单词为单位反转拼接，倒转时会遗漏字符串最后的空格
    public String trans(String s, int n) {
        // write code here
        String temp = reverse(s);
        //倒转时会遗漏字符串最后的空格
        //最后别忘了拼接上字符串最后的空格
        int count = n-temp.length();
        StringBuilder stringBuilder = new StringBuilder();
        //重复count次
        stringBuilder.append(" ".repeat(Math.max(0, count)));
        stringBuilder.append(aToA(temp));
        return stringBuilder.toString();
    }

    public String reverse(String s) {
        String[] s1 = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = s1.length - 1; i >= 0; i--) {
            res.append(s1[i]);
            if (i != 0) {
                res.append(" ");
            }
        }
        //System.out.println(res);
        return res.toString();
    }

    public String aToA(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == ' ') {
                continue;
            }
            //使用Character类中的方法（也可以直接加减）
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                //chars[i]-=32
                chars[i] = Character.toUpperCase(chars[i]);
            }else{
                chars[i] = Character.toLowerCase(chars[i]);
            }
            //额，这里必须是else，不然小写转化为大写后又转化为小写了
//            if (chars[i] >= 'A' && chars[i] <= 'Z') {
//                //chars[i]+=32
//                chars[i] = Character.toLowerCase(chars[i]);
//            }
        }
        //System.out.println(chars);
        //转换为String类再返回
        //Arrays.toString(chars),这种方法返回的值是"[S,A,M,P,L,E, ,A, ,I,S, ,t,H,I,S]"这种样式的
        //chars.toString()这种方法也不能正确返回字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (char aChar : chars) {
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }

    //双逆法，先全部反转，再把每个单词反转一次就可以了
    public String trans2(String s, int n) {
        if(n==0)
            return s;
        StringBuffer res=new StringBuffer();
        for(int i = 0; i < n; i++){
            //大小写转换
            if(s.charAt(i) <= 'Z' && s.charAt(i) >= 'A')
                //也是一种大小写切换的写法
                res.append((char)(s.charAt(i) - 'A' + 'a'));
            else if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                res.append((char)(s.charAt(i) - 'a' + 'A'));
            else
                //空格直接复制
                res.append(s.charAt(i));
        }
        //翻转整个字符串
        //StringBuffer类可以直接反转字符串
        res = res.reverse();
        for (int i = 0; i < n; i++){
            int j = i;
            //以空格为界，二次翻转
            while(j < n && res.charAt(j) != ' ')
                j++;
            String temp = res.substring(i,j);
            StringBuffer buffer = new StringBuffer(temp);
            temp = buffer.reverse().toString();
            res.replace(i,j,temp);
            i = j;
        }
        return res.toString();
    }

    //分割字符串加栈
    public String trans3(String s, int n) {
        if(n==0)
            return s;
        StringBuffer res=new StringBuffer();
        for (int i = 0; i < n; i++){
            //大小写转换
            if(s.charAt(i) <= 'Z' && s.charAt(i) >= 'A')
                res.append((char)(s.charAt(i) - 'A' + 'a'));
            else if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                res.append((char)(s.charAt(i) - 'a' + 'A'));
            else
                //空格直接复制
                res.append((char)(s.charAt(i)));
        }
        Stack<String> temp=new Stack<String>();
        for (int i = 0; i < n; i++){
            int j = i;
            //以空格为界，分割单词
            while(j < n && res.charAt(j) != ' ')
                j++;
            //单词进栈
            temp.push((String)(res.substring(i, j)));
            i = j;
        }
        //排除结尾空格的特殊情况
        if(s.charAt(n - 1) == ' ')
            res = new StringBuffer(" ");
        else
            res = new StringBuffer();
        //栈遵循先进后厨，单词顺序是反的
        while(!temp.empty()){
            res.append(temp.peek());
            temp.pop();
            if(!temp.empty())
                res.append(" ");
        }
        return res.toString();
    }
}
