package nowcoder.shuangzhizhen;
//盛水最多的容器
public class BM93 {
    //双指针法，贪心思想：我们都知道容积与最短边长和底边长有关，与长的底边一定以首尾为边，但是首尾不一定够高，
    // 中间可能会出现更高但是底边更短的情况，因此我们可以使用对撞双指针向中间靠，这样底边长会缩短，因此还想要有更大容积只能是增加最短变长，
    // 此时我们每次指针移动就移动较短的一边，因为贪心思想下较长的一边比较短的一边更可能出现更大容积。
    public int maxArea (int[] height) {
        //排除不能形成容器的情况
        if(height.length < 2)
            return 0;
        int res = 0;
        //双指针左右界
        int left = 0;
        int right = height.length - 1;
        //共同遍历完所有的数组
        while(left < right){
            //计算区域水容量
            int capacity = Math.min(height[left], height[right]) * (right - left);
            //维护最大值
            res = Math.max(res, capacity);
            //优先舍弃较短的边
            if(height[left] < height[right])
                left++;
            else
                right--;
        }
        return res;
    }
}
