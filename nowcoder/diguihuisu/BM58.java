package nowcoder.diguihuisu;

import java.nio.charset.StandardCharsets;
import java.util.*;

//字符串的排列
public class BM58 {
    public ArrayList<String> Permutation (String str) {   //递归回溯
        // write code here
        if(str==null||str.length()==0){
            return new ArrayList<>();
        }
        StringBuilder stringBuilder = new StringBuilder();
        int n =str.length();
        ArrayList<Integer> arrayList = new ArrayList<>();  //记录已经有那几个下标的字符加入
        HashSet<String> hashSet = new HashSet<>();      //用hashset去重
        find(str,n,arrayList,stringBuilder,hashSet);
        ArrayList<String> res = new ArrayList<>();
        res.addAll(hashSet);
        return res;
    }

    public void find(String str, int n,ArrayList<Integer> arrayList,StringBuilder stringBuilder, HashSet<String> hashSet){
        if(arrayList.size()==n){
            hashSet.add(stringBuilder.toString());
            return;
        }
        for (int i = 0; i < n; i++) {
            if(!arrayList.contains(i)){
                stringBuilder.append(str.charAt(i));
                arrayList.add(i);
                find(str,n,arrayList,stringBuilder,hashSet);
                arrayList.remove(arrayList.size()-1);
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
            }
        }
    }

    ArrayList<String> result = new ArrayList<String>();   //回溯记录 ，和方法一一样但会超时
    public ArrayList<String> Permutation2(String str) {
        if(str == null || str.length() == 0) return result;
        char[] charStr = str.toCharArray();
        // 按字典序排序
        Arrays.sort(charStr);
        // 存储字符顺序
        StringBuilder track = new StringBuilder();
        // 存储已经访问的字符下标
        HashMap<Integer, Character> map = new HashMap<>();
        // 回溯遍历
        backtrack(charStr, track, map);
        return result;
    }
    // 回溯法
    public void backtrack(char[] charStr, StringBuilder track, HashMap<Integer, Character> map){
        // 当字符数量等于字符数组长度时，才是一个完整的排列
        if(track.length() == charStr.length){
            // 因为有重复字符串，需要去重
            if(!result.contains(track.toString())){
                result.add(track.toString());
            }
            return;
        }
        // 每次都从0下标开始遍历
        for(int i=0;i<charStr.length;i++){
            if(map.containsKey(i)){
                // 排除已选择的序号（不能直接用值）
                continue;
            }
            // 做选择
            track.append(charStr[i]);
            map.put(i, charStr[i]);
            // 回溯
            backtrack(charStr, track, map);
            // 撤销选择
            track.deleteCharAt(track.length()-1);
            map.remove(i);
        }
    }

    public ArrayList<String> Permutation3(String str) {  //回溯加交换
        ArrayList<String> result = new ArrayList<String>();
        if(str == null || str.length() == 0) return result;
        char[] charStr = str.toCharArray();
        // 使用treeSet来保证结果是按照字典序排列的，不用事先排列
        TreeSet<String> resSet = new TreeSet<String>();
        Permutation(charStr,0,resSet);
        result.addAll(resSet);
        return result;
    }

    public void Permutation(char[] chars,int begin,TreeSet<String> treeSet){
        if(chars==null || chars.length==0 || begin<0 || begin>chars.length-1) { return ; }
        if(begin == chars.length-1){
            // 使用交换，可以节省原来使用的memo记录数据的空间
            treeSet.add(String.valueOf(chars));
        }else{
            for(int i=begin;i<chars.length;i++){
                swap(chars,begin,i);
                // 设定下标从上一次的下一个下标开始，可以减少循环次数
                Permutation(chars,begin+1,treeSet);
                swap(chars,begin,i);
            }
        }
    }

    public void swap(char[] chars,int begin,int i){
        char temp = chars[begin];
        chars[begin] = chars[i];
        chars[i] = temp;
    }


    //用boolean数组标记是否用过，递归回溯
    public void recursion(ArrayList<String> res, char[] str, StringBuffer temp, boolean[] vis){
        //临时字符串满了加入输出
        if(temp.length() == str.length){
            res.add(new String(temp));
            return;
        }
        //遍历所有元素选取一个加入
        for(int i = 0; i < str.length; i++){
            //如果该元素已经被加入了则不需要再加入了
            if(vis[i])
                continue;
            if(i > 0 && str[i - 1] == str[i] && !vis[i - 1])
                //当前的元素str[i]与同一层的前一个元素str[i-1]相同且str[i-1]已经用过了
                //vis[i - 1]为false说明之前已经遍历过，这是回溯之后再次经过，如果两个字符相同会造成重复
                continue;
            //标记为使用过
            vis[i] = true;
            //加入临时字符串
            temp.append(str[i]);
            recursion(res, str, temp, vis);
            //回溯
            vis[i] = false;
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    public ArrayList<String> Permutation4(String str) {
        ArrayList<String> res = new ArrayList<String>();
        if(str == null || str.length() == 0)
            return res;
        //转字符数组
        char[] charStr = str.toCharArray();
        // 按字典序排序
        Arrays.sort(charStr);
        boolean[] vis = new boolean[str.length()];
        //标记每个位置的字符是否被使用过
        Arrays.fill(vis, false);
        StringBuffer temp = new StringBuffer();
        //递归获取
        recursion(res, charStr, temp, vis);
        return res;
    }
}
