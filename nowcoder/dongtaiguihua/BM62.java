package nowcoder.dongtaiguihua;
//斐波那契数列
public class BM62 {
    public int Fibonacci (int n) {
        // write code here
        if(n==1||n==2){
            return 1;
        }
        int temp1 = 1;
        int temp2 = 1;
        int res = 0;
        while(n>2){
            res = temp1 +temp2;
            temp1 = temp2;
            temp2 = res;
            n--;
        }
        return res;
    }

    //递归时间复杂度和空间复杂度都太高
}
