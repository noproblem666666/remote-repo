package Leetcode;

import java.util.LinkedList;

//字符串解码(辅助栈和递归)
public class hot84 {
    public String decodeString(String s) {     //没有考虑到括号和数字之间的嵌套
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;    //存储需要重复的次数
        int temp = 0;     //存储重复字符的长度
        for (int i = 0; i < chars.length; i++) {

            if(chars[i]>='a'&&chars[i]<='z'){
                stringBuilder.append(chars[i]);
            }else{
                count = 0;
                while(chars[i]>='0'&&chars[i]<='9'){
                    count = count*10+chars[i]-48;   //直接用字符对应的ascII表进行计算
                    i++;
                }
                i++;
                temp = 0;
                while(chars[i]>='a'&&chars[i]<='z'){
                    temp++;
                    i++;
                } //这里不用i++了，因为for循环中自带一次
                while(count>0){
                    for(int j = i-temp ;j<i;j++){
                        stringBuilder.append(chars[j]);
                    }
                    count--;
                }
            }
        }
        return stringBuilder.toString();
    }

    public String decodeString2(String s) {     //辅助栈方法（利用栈的先进后出性质存储嵌套的括号）
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>();   //用来存储重复次数的栈
        LinkedList<String> stack_res = new LinkedList<>();    //用来存储重复字符串之前字符的栈
        for(Character c : s.toCharArray()) {
            if(c == '[') {    //碰到左括号就将当前的数字和字符进栈
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;        //放进栈之后将数字和字符串清空
                res = new StringBuilder();
            }
            else if(c == ']') {    //遇到右括号出栈
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for(int i = 0; i < cur_multi; i++) tmp.append(res);     //将括号内的字符重复
                //这里考虑了括号嵌套时括号中的其他小写字母拼接
                res = new StringBuilder(stack_res.removeLast() + tmp);  //拼接到上一层括号内的内容中
            }
            else if(c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");
            else res.append(c);
        }
        return res.toString();
    }


    public String decodeString3(String s) {
        return dfs(s, 0)[0];
    }
    private String[] dfs(String s, int i) {    //递归函数传递字符串和当前遍历到的下标
        StringBuilder res = new StringBuilder();
        int multi = 0;
        while(i < s.length()) {
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9')        //遇到重复的数字就记录下来
                multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
            else if(s.charAt(i) == '[') {      //遇到左括号就进入下一层递归
                String[] tmp = dfs(s, i + 1);
                i = Integer.parseInt(tmp[0]);  //记录返回时的下标，便于继续遍历
                while(multi > 0) {
                    res.append(tmp[1]);     //将返回的该层括号的内容拼接
                    multi--;
                }
            }
            else if(s.charAt(i) == ']')
                return new String[] { String.valueOf(i), res.toString() };   //由于每一层递归都new了一个res，所以这里返回的是该层括号里的内容
            else
                res.append(String.valueOf(s.charAt(i)));
            i++;
        }
        return new String[] { res.toString() };
    }

}
