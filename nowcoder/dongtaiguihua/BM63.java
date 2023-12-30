package nowcoder.dongtaiguihua;
//跳台阶
public class BM63 {

    //递归
    public int jumpFloor (int number) {
        // write code here
        if(number < 0 ){
            return 0;
        }
        if(number==0 || number ==1){
            return 1;
        }
        return jumpFloor(number-1)+jumpFloor(number-2);
    }

    //和斐波那契原理一样，自底向上计算，节省数组
    public int jumpFloor2 (int n) {
        // write code here
        if(n==0||n==1){
            return 1;
        }
        int temp1 = 1;
        int temp2 = 1;
        int res = 0;
        while(n>1){
            res = temp1 +temp2;
            temp1 = temp2;
            temp2 = res;
            n--;
        }
        return res;
    }
}
