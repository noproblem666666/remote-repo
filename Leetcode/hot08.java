package Leetcode;

import java.util.*;

public class hot08 {
    public static void main(String[] args) {
        int[] nums ={0,0,0};
        List<List<Integer>> list = threeSum(nums);
        System.out.println(list);

    }

    //定义三个指针，保证遍历数组中的每一个结果
    //画图，解答
    public static List<List<Integer>> threeSum(int[] nums) {   //    排序加双指针,可以通过
        //定义一个结果集
        List<List<Integer>> res = new ArrayList<>();
        //数组的长度
        int len = nums.length;
        //当前数组的长度为空，或者长度小于3时，直接退出
        if(nums == null || len <3){
            return res;
        }
        //将数组进行排序
        Arrays.sort(nums);
        //遍历数组中的每一个元素
        for(int i = 0; i<len;i++){
            //如果遍历的起始元素大于0，就直接退出
            //原因，此时数组为有序的数组，最小的数都大于0了，三数之和肯定大于0
            if(nums[i]>0){
                break;
            }
            //去重，当起始的值等于前一个元素，那么得到的结果将会和前一次相同
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int l = i +1;
            int r = len-1;
            //当 l 不等于 r时就继续遍历
            while(l<r){
                //将三数进行相加
                int sum = nums[i] + nums[l] + nums[r];
                //如果等于0，将结果对应的索引位置的值加入结果集中
                if(sum==0){
                    // 将三数的结果集加入到结果集中
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //在将左指针和右指针移动的时候，先对左右指针的值，进行判断
                    //如果重复，直接跳过。
                    //去重，因为 i 不变，当此时 l取的数的值与前一个数相同，所以不用在计算，直接跳
                    while(l < r && nums[l] == nums[l+1]) {
                        l++;
                    }
                    //去重，因为 i不变，当此时 r 取的数的值与前一个相同，所以不用在计算
                    while(l< r && nums[r] == nums[r-1]){
                        r--;
                    }
                    //将 左指针右移，将右指针左移。
                    l++;
                    r--;
                    //如果结果小于0，将左指针右移
                }else if(sum < 0){
                    l++;
                    //如果结果大于0，将右指针左移
                }else if(sum > 0){
                    r--;
                }
            }
        }
        return res;
    }



    public static List<List<Integer>> threeSum2(int[] nums) {      //时间复杂度依然达不到要求

//        List是一个接口，而ArrayList是一个类，它实现了List接口。
//        所以List不能被构造，List list=new List()这种写法是错误的，而ArrayList就可以被构造。
//        List list = new ArrayList();这句创建了一个ArrayList的对象后把向上转型成了List。此时它是一个List对象了，有些ArrayList有但是List没有的属性和方法，它就不能再用了。
//        而ArrayList list=new ArrayList();创建一对象则保留了ArrayList的所有属性。
//
//        两者都是集合.
//
//                ArrayList t类型不安全，因为加入的数据为object类型，所以需要装箱与拆箱，效率较低。
//        List：声明时就决定了类型，所以是类型安全的，省掉了装箱与拆箱的过程，效率比ArrayList要高。
//
//        ArrayList就是一个List而已。
//        泛型集合的优势就是类型安全和无装箱。
        Arrays.sort(nums);    //先对nums进行排序，这个函数直接改变数组，不创建新的数组
        List<List<Integer>> list =new ArrayList<>();
        Map<Integer,Integer> map =new HashMap<>();
        int i=0;
        for (int num : nums) {
            map.put(num,i);
            i++;
        }
        for( i=0;i<nums.length;i++){
            for(int j =i+1;j<nums.length;j++){
                int temp =-(nums[i]+nums[j]);
                if(map.containsKey(temp)){     //先判断是否存在，避免报错
                    int index=map.get(temp);
                    if(index==i||index==j){
                    }else{
                        ArrayList<Integer> arrayList1 =new ArrayList<>();
                        arrayList1.add(nums[i]);
                        arrayList1.add(nums[j]);
                        arrayList1.add(nums[index]);
                        Collections.sort(arrayList1);
                        if(!list.contains(arrayList1)){
                            list.add(arrayList1);
                        }

                    }
                }

            }
        }
        return list;
    }
}
