package nowcoder.diguihuisu;

import java.util.ArrayList;
import java.util.Comparator;

//没有重复项数字的全排列
public class BM55 {
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> permute (int[] num) {
        // write code here
        per(num,new ArrayList<>());
        return res;
    }

    public void per(int[] num , ArrayList<Integer> arrayList){
        if(arrayList.size()==num.length){           //递归出口
            res.add(new ArrayList<>(arrayList));    //浅拷贝
            return;
        }
        for (int i : num) {
            if(!arrayList.contains(i)){
                arrayList.add(i);
                per(num,arrayList);
                arrayList.remove(arrayList.size()-1);    //每次出来后需要删掉最后一项元素，不然填完123就会结束
            }
        }
    }

    //非递归方法 ，不过这种方法最后排序不是字典序，还需要自行排序
    ArrayList<ArrayList<Integer>> res2 = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> permute2(int[] num) {
        ArrayList<Integer> list = new ArrayList<>();
        // 先对res中加入一个空的list，给第一次插入制造一个空间。
        res2.add(list);
        // 整个循环的次数为num的元素个数
        for(int i = 0; i < num.length; i++){

            ArrayList<ArrayList<Integer>> tmp = new ArrayList<>();
            // 遍历此时的排列结果
            for(ArrayList<Integer> r:res2){
                // 根据集合的大小，使用for循环在可插入的位置进行插入
                for(int j = 0; j < r.size()+1; j++){
                    // 在第j个位置插入
                    r.add(j,num[i]);
                    // 此时构成新的排列集合，可能是不完整的排列集合（例如：[1,2];[2,1]这类）
                    ArrayList<Integer> temp = new ArrayList<>(r);
                    // 放进去tmp临时集合中
                    tmp.add(temp);
                    // 将刚插入的数移除掉，为了将同样的这个插入不同的位置
                    r.remove(j);
                }
            }
            // 最后赋给res进行返回
            res2 = new ArrayList<>(tmp);
        }
        res2.sort(new Comparator<ArrayList<Integer>>() {   //按照字典序排序
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                int i = 0;
                while(o1.get(i)==o2.get(i)){
                    i++;
                }
                return i==o1.size()?0:o1.get(i)-o2.get(i);
            }
        });
        return res2;
    }
}
