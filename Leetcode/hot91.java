package Leetcode;

import java.util.Arrays;

//汉明距离
public class hot91 {
    public int hammingDistance(int x, int y) {
        int temp = 1;
        int tempX = x;
        int tempY = y;
        while(x/2!=0){
            x=x/2;
            temp++;
        }
        System.out.println(temp);
        int[] x1 = new int[temp];
        temp=1;
        while(y/2!=0){
            y=y/2;
            temp++;
        }
        int[] y1 = new int[temp];

        for (int i = 0; i < x1.length; i++) {      //转换为二进制存储在数组中
            x1[i] = tempX%2;
            tempX=tempX/2;
        }
        for (int i = 0; i < y1.length; i++) {
            y1[i] = tempY%2;
            tempY=tempY/2;
        }
        int count = 0;
        int[] z;
        if(x1.length> y1.length){
            z =x1;
        }else{
            z =y1;
        }
        int length =Math.min(x1.length, y1.length);
        for (int i = 0; i < length; i++) {
            if(x1[i]!=y1[i]){
                count++;
            }
        }
        for(int i = length;i<z.length;i++){
            if(z[i]==1){
                count++;
            }
        }
        return count;
    }

    public int hammingDistance2(int x, int y) {   //使用Java内置的二进制计数函数
        return Integer.bitCount(x ^ y);
    }

    public int hammingDistance3(int x, int y) {     //自己实现移位与位计数
        int s = x ^ y, ret = 0;
        while (s != 0) {
            ret += s & 1;
            s >>= 1;
        }
        return ret;
    }

    public int hammingDistance4(int x, int y) {   //Brian Kernighan 算法
        int s = x ^ y, ret = 0;
        while (s != 0) {
            s &= s - 1;    //记 f(x) 表示 x 和 x−1 进行与运算所得的结果，那么 f(x)恰为x删去其二进制表示中最右侧的1的结果。
            ret++;
        }
        return ret;
    }



}
