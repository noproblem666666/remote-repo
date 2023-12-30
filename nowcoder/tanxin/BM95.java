package nowcoder.tanxin;
//分糖果问题
public class BM95 {
    //贪心算法，用辅助数组，从左到右和从右到左分别遍历一次
    public int candy (int[] arr) {
        int n=arr.length;
        if(n<=1)
            return n;
        int[] nums = new int[n];
        //初始化
        for(int i = 0; i < n; i++)
            nums[i] = 1;
        //从左到右遍历
        for(int i = 1; i < arr.length; i++){
            //如果右边在递增，每次增加一个
            if(arr[i] > arr[i - 1])
                nums[i] = nums[i - 1] + 1;
        }
        //记录总糖果数
        int res = nums[arr.length - 1];
        //从右到左遍历
        for(int i = arr.length - 2; i >= 0; i--){
            //如果左边更大但是糖果数更小
            if(arr[i] > arr[i + 1] && nums[i] <= nums[i + 1])
                nums[i] = nums[i + 1] + 1;
            //累加和
            res += nums[i];
        }
        return res;
    }

    //计算最大坡度，比较巧妙
    public int candy2 (int[] arr) {
        // write code here
        int up = 0, down = 0, peak = 1, res = 1;
        for (int i = 1; i < arr.length; i++) {
            res++;
            if (arr[i] > arr[i - 1]) {
                up++;
                res += up;
                down = 0;
                peak = up + 1;
            }
            else if (arr[i] == arr[i - 1]) {
                up = 0;
                down = 0;
                peak = 1;
            }
            else {
                up = 0;
                res += down;
                down++;
                if (down >= peak)
                    res++;
            }
        }
        return res;
    }
}
