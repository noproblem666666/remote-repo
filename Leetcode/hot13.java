package Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class hot13 {                  //与hot09思路类似
    List<String> resList;    //静态函数不能调用非静态的变量

    public static void main(String[] args) {
        hot13 s =new hot13();
        System.out.println(s.generateParenthesis2(3));
        //System.out.println(resList);  因为主函数是个静态函数，所以不能直接调用非静态变量
    }

    public List<String> generateParenthesis(int n) {
        resList=new ArrayList<>();
        if(n==0){
            return resList;
        }
        generateBlocks(n,n,new StringBuilder());
        return resList;
    }
    public  void generateBlocks(int left,int right,StringBuilder stringBuilder){       //递归 回溯法 借用hot09中的递归思想!!!
        if(left==0&&right==0){          //这个条件必须在上面，不然轮不到它
            resList.add(stringBuilder.toString());   //递归的出口
        }else if(left==0){
            stringBuilder.append(')');
            generateBlocks(left,right-1,stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);        //去掉最后一位，不然这一阶段会多加一个括号
//            valueOf和toString 都定义在Object.prototype上
//                    valueOf会尝试将参数转换为对应的基本数据类型
//            对象类型调用toString方法的时候
//            默认情况下，会根据对象的类型返回格式类似于[object ${Type}]的字符串
//                    某些对象会重写自身的toString方法和valueOf方法
        }else if(left == right){
            stringBuilder.append('(');
            generateBlocks(left-1,right,stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);       //去掉最后一位，不然这一阶段会多加一个括号
        }else{


            stringBuilder.append('(');
            generateBlocks(left-1,right,stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);  //去掉最后一位，不然这一阶段会多加一个括号
            stringBuilder.append(')');
            generateBlocks(left,right-1,stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);    //去掉最后一位，不然这一阶段会多加一个括号
        }
    }

    public List<String> generateParenthesis2(int n) {               //动态规划题解，非常值得学习
        LinkedList<LinkedList<String>> result = new LinkedList<LinkedList<String>>();
        if (n == 0)
            return result.get(0);
        LinkedList<String> list0 = new LinkedList<String>();
        list0.add("");
        result.add(list0);
        LinkedList<String> list1 = new LinkedList<String>();
        list1.add("()");
        result.add(list1);
        for (int i = 2; i <= n; i++) {
            LinkedList<String> temp = new LinkedList<String>();
            for (int j = 0; j < i; j++) {
                List<String> str1 = result.get(j);
                List<String> str2 = result.get(i - 1 - j);
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        String el = "(" + s1 + ")" + s2;
                        temp.add(el);
                    }
                }

            }
            result.add(temp);
        }
        return result.get(n);
    }
}
