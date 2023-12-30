package Leetcode;
//爬楼梯，依然可以用动态规划的思想或者递归
//如果一个问题可与转化为求解一个矩阵的n次方的形式，那么可以用快速幂来加速计算
//也可以用解特征方程的通项公式
public class hot29 {
    public static void main(String[] args) {
        int n=3;
        hot29 s = new hot29();
        System.out.println(s.climbStairs2(n));
    }
    public int climbStairs(int n) {    //递归思想，时间复杂度不满足要求
        return climb(n);
    }
    public int climb(int n){
        if(n<2){
            return 1;
        }else{
            return climb(n-2)+climb(n-1);
        }
    }

    public int climbStairs2(int n){
        int[] index=new int[n+1];   //长度应该为n+1，因为我们需要算到index[n]  ！！！
        index[0]=1;
        index[1]=1;
        for(int i=2;i<n+1;i++){     //这里的边界条件也要改，不然算不到
            index[i]=index[i-1]+index[i-2];
            //System.out.println(index[i]);
        }
        return index[n];
    }
}
