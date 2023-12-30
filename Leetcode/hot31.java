package Leetcode;

//颜色分类，不能使用sort函数，只扫描一遍
public class hot31 {
    public void sortColors(int[] nums) {   //给定的数组长度为1-300
        int left = 0;
        int right = nums.length - 1;   //使用双指针只需遍历一次
        while (left < right) {
            while (left < right && nums[left] == 0) {   //加上边界条件防止越界
                left++;
            }
            while (left < right && nums[right] == 2) {
                right--;
            }
            int index = left;//这时left指向的数肯定不是0，right指向的数也肯定不是2
            while (index < right && nums[index] == 1) {      //遇到1就跳过，遇到0就换到前面，遇到2就换到后面
                index++;
            }
            if (index > right) {     //退出循环的条件，注意不能是等于，因为right指向的数字并不一定排序完成，right和left指向的是排序完成的下一位
                break;
            } else if (nums[index] == 0) {
                int temp = nums[index];
                nums[index] = nums[left];
                nums[left] = temp;
                left++;
            } else {
                int temp = nums[index];
                nums[index] = nums[right];
                nums[right] = temp;
                right--;
            }

        }
    }
}
