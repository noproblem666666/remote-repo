package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class hot09 {
    public static void main(String[] args) {
        String digits = "23";
        hot09 s =new hot09();
        System.out.println(s.letterCombinations(digits));
    }

    List<String> res;//最终输出结果res
    //数字到字符串的映射表，如letter_map[2]="abc",即数字2对应abc
    String[] Map = {" ", " ", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return res;
        interDigits(digits, new StringBuilder(), 0);
        return res;
    }

    //递归函数
    private void interDigits(String digits, StringBuilder letter, int index) {
        //letter用来保存每次组合的结果
        //每进行完一次组合后，就走到递归边界，此时保存组合结果
        if (index == digits.length()) {
            res.add(letter.toString());
            return;
        }
        //pos:获取映射表letter_map索引，如index是2，有'2'-'0'=2，获取下标为2,letter_map[2]就是"abc"
        int pos = digits.charAt(index) - '0';//index:即每个数字的索引
        String MapString = Map[pos];//获取index对应的数字的字符串
        //遍历字符串
        for (int i = 0; i < MapString.length(); i++) {
            //程序递归部分
            letter.append(MapString.charAt(i));
            interDigits(digits, letter, index + 1);//下一个数字
            letter.deleteCharAt(letter.length() - 1);//清除上次保存的字符,防止脏数据(每次递归结束后，letter仍保存着上次的组合结果)
        }
    }
    public List<String> letterCombinations2(String digits) {     //用队列和循环进行组合
        if(digits==null || digits.length()==0) {
            return new ArrayList<String>();
        }
        //一个映射表，第二个位置是"abc“,第三个位置是"def"。。。
        //这里也可以用map，用数组可以更节省点内存
        String[] letter_map = {
                " ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"
        };
        List<String> res = new ArrayList<>();
        //先往队列中加入一个空字符
        res.add("");
        for(int i=0;i<digits.length();i++) {
            //由当前遍历到的字符，取字典表中查找对应的字符串
            String letters = letter_map[digits.charAt(i)-'0'];
            int size = res.size();
            //计算出队列长度后，将队列中的每个元素挨个拿出来
            for(int j=0;j<size;j++) {
                //每次都从队列中拿出第一个元素
                String tmp = res.remove(0);
                //然后跟"def"这样的字符串拼接，并再次放到队列中
                for(int k=0;k<letters.length();k++) {
                    res.add(tmp+letters.charAt(k));
                }
            }
        }
        return res;
    }


}
