package Leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class hot62 {
    class Trie {                                          //用了很多的hashmap，时间复杂度和空间复杂度都很高
        HashMap<Integer, List<String>> startsWithMap;      //这个用来存储前缀
        HashMap<Integer,List<String >> searchMap;          //这个用来存储原字符串
        public Trie() {
            startsWithMap = new HashMap<>();
            searchMap = new HashMap<>();

        }

        public void insert(String word) {
            int length = word.length();
            if(!searchMap.containsKey(length)){
                searchMap.put(length,new LinkedList<>());
            }
            searchMap.get(length).add(word);
            for (int i = length; i > 0; i--) {        //把这个字符串的前几位全都输入hashmap存储起来，可以根据字符串长度进行查询
                String subString = word.substring(0,i);     //因为本身也算前缀，所以i不用从length-1处开始
                if(!startsWithMap.containsKey(i)){
                    startsWithMap.put(i,new LinkedList<>());
                }
                startsWithMap.get(i).add(subString);
            }
        }

        public boolean search(String word) {
            return find(word, searchMap);
        }

        public boolean startsWith(String prefix) {
            return find(prefix, startsWithMap);
        }

        private boolean find(String prefix, HashMap<Integer, List<String>> startsWithMap) {     //用来查找是否有字符串或者前缀的方法
            if(prefix.length()==0){
                return false;
            }
            int length = prefix.length();
            if(!startsWithMap.containsKey(length)){
                return false;
            }
            List<String> list = startsWithMap.get(length);
            return list.contains(prefix);
        }
    }

    class Trie2 {                //标准答案（字典树，前缀树）（一次建树，多次查询）

        class TireNode {
            private boolean isEnd;
            TireNode[] next;

            public TireNode() {
                isEnd = false;           //用来指示当前字母是否是一个单词的结尾
                next = new TireNode[26];   //从根结点开始，每个结点都可能有26个子结点，代表后续的26个小写英文字母
            }
        }

        private TireNode root;

        public Trie2() {
            root = new TireNode();
        }

        public void insert(String word) {
            TireNode node = root;
            for (char c : word.toCharArray()) {
                if (node.next[c - 'a'] == null) {       //利用字符的特性，用字符加减转换为数组坐标
                    node.next[c - 'a'] = new TireNode();
                }
                node = node.next[c - 'a'];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            TireNode node = root;
            for (char c : word.toCharArray()) {
                node = node.next[c - 'a'];
                if (node == null) {       //如果这个字母没有被存储，就说明单词不存在
                    return false;
                }
            }
            return node.isEnd;            //即使字母都存在，也要确认其是单词而不是前缀
        }

        public boolean startsWith(String prefix) {
            TireNode node = root;
            for (char c : prefix.toCharArray()) {
                node = node.next[c - 'a'];
                if (node == null) {
                    return false;
                }
            }
            return true;                  //前缀不需要确认，只要字母都存在就认为存在
        }
    }
}
