package Leetcode;

import java.util.*;
import java.util.function.BiConsumer;

class hot03 {
    public static void main(String[] args) {
        String str = "dawabuvafbefqoibwqjdvoanjeawad";
        System.out.println(lengthOfLongestSubstring2(str));
    }

    public static int lengthOfLongestSubstring(String s) {           //方法还是不对，只考虑了一对相同字符产生的字串，其中可能包含其他相同的字符
        HashMap<Character, ArrayList<Integer>> hashMap = new HashMap<>();  //Character 存放字符串中出现的字符，链表用来存放其出现的位置

        char[] chars = s.toCharArray();
        int i = 0;
        for (char c : chars) {
            if (hashMap.containsKey(c)) {   //如果已经出现过则加入其现在的位置
                ArrayList<Integer> arrayList = hashMap.get(c);
                arrayList.add(i);
            } else {                       //如果没有出现过则新加一个键值对，链表中放入其当前位置
                ArrayList<Integer> arrayList = new ArrayList<>();
                arrayList.add(i);
                hashMap.put(c, arrayList);
            }
            i++;
        }
        int index = 0;
        int[] max = new int[hashMap.size()];
        int k = 0;
        for (Map.Entry<Character, ArrayList<Integer>> entry : hashMap.entrySet()) {

            char c = entry.getKey();
            ArrayList<Integer> arrayList = entry.getValue();
            Collections.sort(arrayList); //从小到大排序
            int[] length = new int[arrayList.size() + 1];  //用来存放算出的子串长度
            length[0] = arrayList.get(0) + 1;
            for (int temp = 1; temp < length.length - 1; temp++) {
                length[temp] = arrayList.get(temp) - arrayList.get(temp - 1);
            }
            length[arrayList.size()] = s.length() - arrayList.get(arrayList.size() - 1);
            System.out.println(c+"  "+arrayList);
            System.out.println(c + "分割出的无重复字串长度为" + Arrays.toString(length));
            for (int i1 : length) {
                if(i1>max[k]){
                    max[k] = i1;
                }
            }
            k++;
        }
        System.out.println(Arrays.toString(max));
        int min = max[0];
        for (int max1 : max) {
            if(max1<min){
                min = max1;
            }
        }
        return min;
    }



    public static int  lengthOfLongestSubstring1(String s) {
        /**
         * 暴力解法：引发超出时间限制问题（TLE）
         * 优化1：将j从后往前遍历，若遇到子字符串无重复字符时，赋值ans，并中断里层循环，这个小优化依旧会导致TLE
         **/
        int result = s.length();
        int ans = 0;
        for (int i=0; i<result; i++) {
            for (int j=i+1; j<=result; j++) {
                if (allUnique(s, i, j)) {
                    ans = Math.max(ans, j-i);
                }
            }
        }
        return ans;
    }
    //判断字符串中是否存在重复的字符
    public static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i=start; i<end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }

    public static int  lengthOfLongestSubstring2(String s) {     //滑动窗口，始终确保窗口中不存在相同的字符,时间复杂度为o(n)
        int n = s.length();
        int ans = 0 ;
        HashMap<Character,Integer> hashMap = new HashMap<>();
        char[] chars = s.toCharArray();
        int start = 0;
        int end = 0;
        for(;end< chars.length;end++){
            char c = chars[end];
            if(hashMap.containsKey(c)){
                start = Math.max(hashMap.get(c),start);   //防止start之前已更新导致其返回
            }
            ans = Math.max(end-start+1,ans);
            hashMap.put(c,end+1);
        }

        return ans;

    }


}
