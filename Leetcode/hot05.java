package Leetcode;

public class hot05 {
    public static void main(String[] args) {
        String arr ="aacabdkacaa";
        int max = 0;
        int start = 0;
        int index=0;
        while(start<arr.length()){
            int i = med(arr,start);
            if(max<i){
                max = i;
                index=start;
            }
            start++;
        }
        System.out.println(index);
        System.out.println(max);
        if(max%2==1){  //最长回文子串是奇数的情况
            int before =index-(max-1)/2;
            int after = index+(max-1)/2;
            System.out.println(arr.substring(before,after+1));
        }
        if(max%2==0){
            int before = index -(max-2)/2;
            int after = index+1+(max-2)/2;
            System.out.println(arr.substring(before,after+1));
        }


    }
    public static int med(String arr,int i){    //从i索引开始找字符串中的最长回文子串
        int b1 = i;
        int b2 = i;
        int max1 = 1;
        int max2=0;
        b1--;
        b2++;
        while(b1>=0&&b2<arr.length()){  //计算奇数的最长回文子串

            if(arr.charAt(b1)==arr.charAt(b2)){
                max1+=2;
            }else{     //只要有对不上的就得退出
                break;
            }
            b1--;
            b2++;
        }
        b1=i;
        b2=i+1;
        while(b1>=0&&b2<arr.length()){       //计算偶数的最长回文子串
            if(arr.charAt(b1)==arr.charAt(b2)){
                max2+=2;
            }else{
                break;   //只要有对不上的就得退出
            }
            b1--;
            b2++;
        }
        return Math.max(max1,max2);

    }
}
