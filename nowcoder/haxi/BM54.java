package nowcoder.haxi;

import java.util.*;

//三数之和
public class BM54 {
    //二维辅助数组和和哈希表
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        // write code here
        //辅助二维数组，记录num[i]+num[j]的值
        int[][] temp = new int[num.length][num.length];
        //方便找到每个数的下标
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        HashSet<ArrayList<Integer>> hashSet = new HashSet<>(); //复杂去重
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            for (int j = i + 1; j < num.length; j++) {
                temp[i][j] = num[i] + num[j];
            }
        }
        for (int i = 0; i < num.length; i++) {
            hashMap.put(num[i], i);
        }
        for (int i = 0; i < num.length; i++) {
            for (int j = i + 1; j < num.length; j++) {
                if (hashMap.containsKey(-temp[i][j]) && hashMap.get(-temp[i][j]) != i && hashMap.get(-temp[i][j]) != j) {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(-temp[i][j]);
                    arrayList.add(num[i]);
                    arrayList.add(num[j]);
                    Collections.sort(arrayList);
                    hashSet.add(arrayList);
                }
            }
        }
        //ArrayList<ArrayList<Integer>> res = new ArrayList<>(hashSet);
        res.addAll(hashSet);//不用增强for或者迭代器循环遍历，可以直接用addAll方法或者加在新建链表中
        res.sort(new Comparator<ArrayList<Integer>>() {      //Todo：链表的自定义排序
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                int i = 0;
                while(i<o1.size()&&o1.get(i)==o2.get(i)){
                    i++;
                }
                return i==o1.size()?0:o1.get(i)-o2.get(i);    //升序
            }
        });
        return res;
    }

    //双指针，先排序，根据当前总和大小调整双指针
    public ArrayList<ArrayList<Integer>> threeSum2(int[] num) {
        ArrayList<ArrayList<Integer> > res = new ArrayList<ArrayList<Integer>>();
        int n = num.length;
        //不够三元组
        if(n < 3)
            return res;
        //排序
        Arrays.sort(num);
        for(int i = 0; i < n - 2; i++){
            if(i != 0 && num[i] == num[i - 1])
                continue;
            //后续的收尾双指针
            int left = i + 1;
            int right = n - 1;
            //设置当前数的负值为目标
            int target = -num[i];
            while(left < right){
                //双指针指向的二值相加为目标，则可以与num[i]组成0
                if(num[left] + num[right] == target){
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(num[i]);
                    temp.add(num[left]);
                    temp.add(num[right]);
                    res.add(temp);
                    while(left + 1 < right && num[left] == num[left + 1])
                        //去重
                        left++;
                    while(right - 1 > left && num[right] == num[right - 1])
                        //去重
                        right--;
                    //双指针向中间收缩
                    left++;
                    right--;
                }
                //双指针指向的二值相加大于目标，右指针向左
                else if(num[left] + num[right] > target)
                    right--;
                    //双指针指向的二值相加小于目标，左指针向右
                else
                    left++;
            }
        }
        return res;
    }
}
