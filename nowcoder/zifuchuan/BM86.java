package nowcoder.zifuchuan;

import java.math.BigDecimal;
import java.util.Stack;

//大数加法
public class BM86 {
    //模拟法
    //全部按照字符串从后到前加，注意进位，最后别忘了可能的进位，用StringBuilder翻转下
    public String solve (String s, String t) {
        // write code here
        if(s.equals("")){
            return t;
        }else if(t.equals("")){
            return s;
        }
        StringBuilder res = new StringBuilder();
        int sLength = s.length()-1;
        int tLength = t.length()-1;
        //记录进位
        int temp = 0;
        while(sLength>=0&&tLength>=0){
            //数字和字符之间的转换需要先转换成字符串
            int count = Integer.parseInt(String.valueOf(s.charAt(sLength))) + Integer.parseInt(String.valueOf(t.charAt(tLength)));
            if(temp == 1){
                count++;
                temp = 0;
            }
            if(count>=10){
                temp = 1;
                count-= 10;
            }
            res.append(count);
            sLength--;
            tLength--;
        }
        if(sLength<0&&tLength<0){
            if(temp ==1){
                res.append(1);
            }
            return res.reverse().toString();
        }else if(sLength>=0){
            while(sLength>=0){
                int count = Integer.parseInt(String.valueOf(s.charAt(sLength)));
                if(temp == 1){
                    count++;
                    temp = 0;
                }
                if(count>=10){
                    temp = 1;
                    count-= 10;
                }
                res.append(count);
                sLength--;
            }
            if(temp ==1){
                res.append(1);
            }
            return res.reverse().toString();
        }else{
            while(tLength>=0){
                int count = Integer.parseInt(String.valueOf(t.charAt(tLength)));
                if(temp == 1){
                    count++;
                    temp = 0;
                }
                if(count>=10){
                    temp = 1;
                    count-= 10;
                }
                res.append(count);
                tLength--;
            }
            if(temp ==1){
                res.append(1);
            }
            return res.reverse().toString();
        }
    }

    //也是模拟法，但使用栈
    public String solve2 (String s, String t) {
        // write code here
        Stack<Integer> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        int i = s.length() - 1, j = t.length() - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            carry += i >= 0 ? s.charAt(i--) - '0' : 0;
            carry += j >= 0 ? t.charAt(j--) - '0' : 0;
            stack.push(carry % 10);
            carry = carry / 10;
        }
        while (!stack.isEmpty())
            stringBuilder.append(stack.pop());
        return stringBuilder.toString();
    }

    //直接使用BigDecimal
    public String solve3 (String s, String t) {
        // write code here
        BigDecimal res = new BigDecimal(s);
        return String.valueOf(res.add(new BigDecimal(t)));

    }
}
