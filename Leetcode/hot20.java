package Leetcode;


import java.util.Stack;

public class hot20 {
    public static void main(String[] args) {
        int[] height = {4, 2, 0, 3, 2, 5};
        hot20 s = new hot20();
        System.out.println(s.trap(height));
    }
    /*
    想法：从高度1开始算起直到最高高度，每个高度找到最左和最右满足该高度的两个坐标，
    这个高度盛满的雨水量就是（两个坐标相减-1-中间还满足该高度的坐标数量），最后相加每个高度的盛满雨水量即可
    * */
    public int trap(int[] height) {           //自己写的，时间复杂度为o(maxheight*length),时间复杂度太高，不满足要求
        int maxHeight = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > maxHeight) {
                maxHeight = height[i];     //先求出最大高度，确定循环边界
            }
        }
        //System.out.println("最大高度为："+maxHeight);
        int i = 1;    //从高度1开始算起
        int sum = 0;   //记录总雨水量
        while (i <= maxHeight) {
            int left = -1;       //记录每一高度最左边坐标()初始值不能为0，因为坐标从0开始，导致数组坐标0满足时没有记录
            int right = 0;      //记录每一高度最右边坐标
            int index = 0;     //记录每一高度满足的坐标个数

            for (int i1 = 0; i1 < height.length; i1++) {    //计算满足的坐标
                if (height[i1] >= i) {
                    if (left == -1) {      //第一次记录的就是最左边的
                        left = i1;
                    }
                    index++;
                    right = i1;     //循环执行到最后记录的就是最右边的
                }
            }
            //System.out.println("左右和坐标数量");
            //System.out.println(left);
            // System.out.println(right);
            //System.out.println(index);

            if (left == right) {
                sum += 0;    //说明只有一个满足（不会没有满足的哦）
            } else {
                sum += right - left - 1 - (index - 2);   //(index-2)是指中间的坐标肯定要去掉左右两边的两个坐标
            }
            i++;
        }
        return sum;
    }

    public int trap2(int[] height) {      //按每列可以容纳的水量求解，每列的水量等于该列两边最高的墙高度较小值
                                          // -该列的高度，如果这个较小值小于等于该列高度则没有积水，时间复杂度为o(length*length)
        int sum = 0;
        //最两端的列不用考虑，因为一定不会有水。所以下标从 1 到 length - 2
        for (int i = 1; i < height.length - 1; i++) {
            int max_left = 0;
            //找出左边最高
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }
            int max_right = 0;
            //找出右边最高
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > max_right) {
                    max_right = height[j];
                }
            }
            //找出两端较小的
            int min = Math.min(max_left, max_right);
            //只有较小的一段大于当前列的高度才会有水，其他情况不会有水
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

    public int trap3(int[] height) {      //动态规划，对解法2优化  时间复杂度o(length),空间复杂度o(length)
        int sum = 0;
        int[] max_left = new int[height.length];     //数组的初始化默认值为0
        int[] max_right = new int[height.length];

        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }
/*解法四：双指针，针对解法二优化空间复杂度
对于每一个柱子接的水，那么它能接的水=min(左右两边最高柱子）-当前柱子高度，这个公式没有问题。同样的，两根柱子要一起求接水，同样要知道它们左右两边最大值的较小值。
问题就在这，假设两柱子分别为 i，j。那么就有 iLeftMax,iRightMax,jLeftMx,jRightMax 这个变量。由于 j>i ，故 jLeftMax>=iLeftMax，iRigthMax>=jRightMax.
那么，如果 iLeftMax>jRightMax，则必有 jLeftMax >= jRightMax，所有我们能接 j 点的水。
如果 jRightMax>iLeftMax，则必有 iRightMax >= iLeftMax，所以我们能接 i 点的水。
而上面我们实际上只用到了 iLeftMax，jRightMax 两个变量，故我们维护这两个即可。
* */
public int trap4(int[] height) {
    int sum = 0;
    int max_left = 0;
    int max_right = 0;
    int left = 1;
    int right = height.length - 2; // 加右指针进去
    for (int i = 1; i < height.length - 1; i++) {
        //从左到右更
        if (height[left - 1] < height[right + 1]) {
            max_left = Math.max(max_left, height[left - 1]);
            int min = max_left;
            if (min > height[left]) {
                sum = sum + (min - height[left]);
            }
            left++;
            //从右到左更
        } else {
            max_right = Math.max(max_right, height[right + 1]);
            int min = max_right;
            if (min > height[right]) {
                sum = sum + (min - height[right]);
            }
            right--;
        }
    }
    return sum;
}


    public int trap5(int[] height) {     //栈，相当于括号匹配     时间复杂度和空间复杂度都是o(length)
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.empty()) { // 栈空就出去
                    break;
                }
                int distance = current - stack.peek() - 1; //两堵墙之前的距离。
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            stack.push(current); //当前指向的墙入栈
            current++; //指针后移
        }
        return sum;
    }

}
