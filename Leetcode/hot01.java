package Leetcode;

//找到两数之和的索引
class hot01 {
    public static void main(String[] args) {
        int[] nums = {5, 21, 1, 6, 2, 8, 95, 94, 466, 5464, 94, 45, 12, 15};
        int[] nums1 = {5, 21, 1, 6, 2, 8, 95, 94, 466, 5464, 94, 45, 12, 15};
        quickSort(nums, 0, 13);
//        for (int value : nums) {
//            System.out.print(value+" ");
//        }
        int target = 10;
        int i = 0;
        int j = 13;
        int[] num = new int[2];
        while (nums[i] + nums[j] != target && i < j) {
            if (nums[i] + nums[j] < target) {
                i++;
            } else if (nums[i] + nums[j] > target) {
                j--;
            }
        }
        if (nums[i] + nums[j] == target) {
            int k = 0;
            for (int i1 = 0; i1 < nums1.length; i1++) {
                if (nums1[i1] == nums[i] || nums1[i1] == nums[j]) {
                    num[k] = i1;
                    k++;
                }
            }
        }
        for (int k : num) {
            System.out.println(k);
        }
        num = twoSum(nums1,10);

        for (int k : num) {
            System.out.println(k);
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        //先进行快速排序
        int[] temp=new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[i]=nums[i];
        }
        quickSort(temp,0,temp.length-1);

        int[] num = new int[2];
        int i = 0;
        int j = temp.length-1;
        while (temp[i] + temp[j] != target && i < j) {

            if (temp[i] + temp[j] < target) {
                i++;
            } else if (temp[i] + temp[j] > target) {
                j--;
            }
        }
        if (temp[i] + temp[j] == target) {
            int k = 0;
            for (int i1 = 0; i1 < nums.length; i1++) {
                if (nums[i1] == temp[i] || nums[i1] == temp[j]) {

                    num[k] = i1;
                    k++;
                }
            }
        }
        return num;
    }

    public static void quickSort(int[] nums, int start, int end) {
        int i = start;
        int j = end;
        if (start > end) {
            return;
        }
        int baseNumber = nums[start];
        while (start < end) {
            while (start < end && nums[end] >= baseNumber) {
                end--;
            }
            while (start < end && nums[start] <= baseNumber) {
                start++;
            }
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
        int temp = nums[start];
        nums[start] = baseNumber;
        nums[i] = temp;

        quickSort(nums, i, start - 1);
        quickSort(nums, start + 1, j);
    }
}