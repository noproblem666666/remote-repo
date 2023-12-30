package Leetcode;

import java.util.*;

//接雨水
public class rehot20 {
    //Todo:动态规划，双指针从左右向中间靠拢，每次移动较小的，分别保留左右的最大边，遇到更大的就替换，遇到更小的就统计雨水
    //用双指针代替了最长边的数组，节省了空间
    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int maxLeft = height[left];
        int maxRight = height[right];
        int sum = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                left++;
                if (height[left] > maxLeft) {
                    maxLeft = height[left];
                } else {
                    sum += maxLeft - height[left];
                }
            } else {
                right--;
                if (height[right] > maxRight) {
                    maxRight = height[right];
                } else {
                    sum += maxRight - height[right];
                }
            }
        }
        return sum;
    }

    //计算每一层的雨水量
    public int trap2(int[] height) {
        int sum = 0;
        int max = getMax(height);//找到最大的高度，以便遍历。
        for (int i = 1; i <= max; i++) {
            boolean isStart = false; //标记是否开始更新 temp
            int temp_sum = 0;
            for (int j = 0; j < height.length; j++) {
                if (isStart && height[j] < i) {
                    temp_sum++;
                }
                if (height[j] >= i) {
                    sum = sum + temp_sum;
                    temp_sum = 0;
                    isStart = true;
                }
            }
        }
        return sum;
    }
    private int getMax(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
            }
        }
        return max;
    }

    //计算每一列的雨水量（第一种方法其实就是这种方法的改进）
    public int trap3(int[] height) {
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



    public int trap4(int[] height) {
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
