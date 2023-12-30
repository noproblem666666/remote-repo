package nowcoder.shuangzhizhen;
//接雨水问题（双指针往中间靠）
public class BM94 {
    //指针往中间遍历，遇到更低柱子就是底，用较短的边界减去底就是这一列的接水量，遇到更高的柱子就是新的边界，更新边界大小。
    public long maxWater (int[] arr) {
        //排除空数组
        if(arr.length == 0)
            return 0;
        long res = 0;
        //左右双指针
        int left = 0;
        int right = arr.length - 1;
        //中间区域的边界高度
        int maxL = 0;
        int maxR = 0;
        //直到左右指针相遇
        while(left < right){
            //每次维护往中间的最大边界
            maxL = Math.max(maxL, arr[left]);
            maxR = Math.max(maxR, arr[right]);
            //较短的边界确定该格子的水量
            if(maxR > maxL)
                res += maxL - arr[left++];
            else
                res += maxR - arr[right--];
        }
        return res;
    }
}
