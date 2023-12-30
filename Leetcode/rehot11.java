package Leetcode;

import java.util.*;

//有效的括号
public class rehot11 {
    //deque是双端队列，可以当队列也能当栈
    //add/offer和poll/remove是队列操作，push和pop才是栈操作
    public boolean isValid(String s) {
        Deque<Character> deque = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if(aChar=='('||aChar=='{'||aChar=='['){
                deque.push(aChar);
            }else{
                if(deque.isEmpty()){
                    return false;
                }
                char a = deque.pop();
                if(aChar==')'&&a!='('){
                    return false;
                }
                if(aChar=='}'&&a!='{'){
                    return false;
                }
                if(aChar==']'&&a!='['){
                    return false;
                }
            }
        }
        return deque.isEmpty();
    }


    //答案写法，用map匹配更简洁方便
    private static final Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')'); put('?','?');
    }};
    public boolean isValid2(String s) {
        if(s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<Character>() {{ add('?'); }};
        for(Character c : s.toCharArray()){
            if(map.containsKey(c)) stack.addLast(c);
            else if(map.get(stack.removeLast()) != c) return false;
        }
        return stack.size() == 1;
    }


}
