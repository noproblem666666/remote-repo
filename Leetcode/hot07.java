package Leetcode;

public class hot07 {
    public static void main(String[] args) {
        int [] height ={1,1};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        int i=0;
        int j=height.length-1;
        int max = 0;
        while(j>i){
            int area = Math.min(height[i],height[j])*(j-i);
            if(area>max){
                max=area;
            }
            if(height[i]>height[j]){    //因为水箱高度取决于最低的height，所以如果向内移动较长板，则水箱一定变小
                j--;
            }else{
                i++;
            }
        }
        return max;
    }
}
