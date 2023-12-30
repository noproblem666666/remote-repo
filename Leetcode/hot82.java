package Leetcode;
//比特位计数
public class hot82 {
    public int[] countBits(int n) {     //对每一位都不停除2计算，时间复杂度为o(n*log(n))
        int[] res = new int[n+1];
        for (int i = 0; i <= n; i++) {
            int count = 0;
            int temp = i;
            while(temp!=0){
                if(temp%2==1){
                    count++;
                }
                temp = temp/2;
            }
            res[i] = count;
        }
        return res;
    }
    public int[] countBits2(int n) {     //动态规划，时间复杂度为o(n)
        int[] bits = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {     //判断i是不是2的整数次幂
                highBit = i;              //将标记更换为当前最高的2的整数次幂
            }
            bits[i] = bits[i - highBit] + 1;  //除0外该位数二进制的最高位一定为1，所以应该是减去最高二次幂数的状态加一
        }
        return bits;
    }


    public int[] countBits3(int num) {       // 动态规划，时间复杂度为o(n)
        int[] result = new int[num + 1];
        for(int i = 1; i <= num; i++){
            if ((i & 1) == 0){
                result[i] = result[i >> 1];       //如果一个数是偶数，那么他的1的个数一定和自身除以2以后一样多
            }else {
                result[i] = result[i - 1] + 1;    //如果一个数是奇数，那么他的1的个数一定比上一个偶数多1
            }
        }
        return result;
    }

}
