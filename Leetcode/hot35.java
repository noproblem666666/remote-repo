package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

//柱状图中最大的矩形（栈）
public class hot35 {
    public int largestRectangleArea(int[] heights) {   //暴力解法，对于每个高度分别扩散查找，超时
        int len = heights.length;
        // 特判
        if (len == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < len; i++) {

            // 找左边最后 1 个大于等于 heights[i] 的下标
            int left = i;
            int curHeight = heights[i];
            while (left > 0 && heights[left - 1] >= curHeight) {
                left--;
            }

            // 找右边最后 1 个大于等于 heights[i] 的索引
            int right = i;
            while (right < len - 1 && heights[right + 1] >= curHeight) {
                right++;
            }

            int width = right - left + 1;
            res = Math.max(res, width * curHeight);
        }
        return res;
    }

    /*
    遍历每个高度，是要以当前高度为基准，寻找最大的宽度 组成最大的矩形面积那就是要找左边第一个小于当前高度的下标left，
    再找右边第一个小于当前高度的下标right 那宽度就是这两个下标之间的距离了 但是要排除这两个下标
    所以是right-left-1 用单调栈就可以很方便确定这两个边界了
    * */

    public int largestRectangleArea2(int[] heights) {    //单调栈，引入哨兵进行简化（栈里存储的是下标而不是高度，可以根据下标找高度并且计算宽度）
        int len = heights.length;
        if (len == 0) {
            return 0;
        }

        if (len == 1) {
            return heights[0];
        }

        int res = 0;

        int[] newHeights = new int[len + 2];
        newHeights[0] = 0;
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[len + 1] = 0;
        len += 2;
        heights = newHeights;

        Deque<Integer> stack = new ArrayDeque<>(len);
        // 先放入哨兵，在循环里就不用做非空判断
        stack.addLast(0);

        for (int i = 1; i < len; i++) {
            while (heights[i] < heights[stack.peekLast()]) {
                int curHeight = heights[stack.pollLast()];
                int curWidth = i - stack.peekLast() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }
        return res;
    }



}
