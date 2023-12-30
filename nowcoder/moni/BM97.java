package nowcoder.moni;

import java.util.Arrays;

//旋转数组（不使用额外的数组）
public class BM97 {
    public int[] solve (int n, int m, int[] a) {
        // write code here
        //去掉多余的重复操作
        m = m % n;
        if(m==0){
            return a;
        }
        //分两种情况，如果能整除，则要对数组分m次操作，如果不能，则一直移动到回到原点
        if(n%m==0){
            for (int i = 0; i < m; i++) {
                //需要两个变量来记录交换的数字
                int index = i;
                int temp1 = a[index];
                int temp2 = a[index];
                while(index+m<n){
                    temp2 = a[index+m];
                    a[index+m] = temp1;
                    temp1 = temp2;
                    index += m;
                }
                a[i] = temp1;
            }
        }else{
            int index = 0;
            int temp1 = a[index];
            int temp2 = a[index];
            while((index+m)%n!=0){
                temp2 = a[(index+m)%n];
                a[(index+m)%n] = temp1;
                temp1= temp2;
                index+=m;
                index = index % n;
            }
            a[0] = temp1;
            System.out.println(Arrays.toString(a));
        }
        return a;
    }

    //答案三次翻转
    public int[] solve2 (int n, int m, int[] a) {
        //取余，因为每次长度为n的旋转数组相当于没有变化
        m = m % n;
        //第一次逆转全部数组元素
        reverse(a, 0, n - 1);
        //第二次只逆转开头m个
        reverse(a, 0, m - 1);
        //第三次只逆转结尾m个
        reverse(a, m, n - 1);
        return a;
    }
    //反转函数
    public void reverse(int[] nums, int start, int end){
        while(start < end){
            swap(nums, start++, end--);
        }
    }
    //交换函数
    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
