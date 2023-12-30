package nowcoder.erfen;
//比较版本号（比较字符串）
public class BM22 {
    public int compare (String version1, String version2) {      //双指针计算每一段的数字大小
        int n1 = version1.length();
        int n2 = version2.length();
        int i = 0, j = 0;
        //直到某个字符串结束
        while(i < n1 || j < n2){
            long num1 = 0;
            //从下一个点前截取数字
            while(i < n1 && version1.charAt(i) != '.'){
                num1 = num1 * 10 + (version1.charAt(i) - '0');
                i++;
            }
            //跳过点
            i++;
            long num2 = 0;
            //从下一个点前截取数字
            while(j < n2 && version2.charAt(j) != '.'){
                num2 = num2 * 10 + (version2.charAt(j) - '0');
                j++;
            }
            //跳过点
            j++;
            //比较数字大小
            if(num1 > num2)
                return 1;
            if(num1 < num2)
                return -1;
        }
        //版本号相同
        return 0;
    }

    public int compare2 (String version1, String version2) {    //分割字符串再计算数字大小
        //按照.划分
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        for(int i = 0; i < nums1.length || i < nums2.length; i++){
            //较短的版本号后续都取0
            String str1 = i < nums1.length ? nums1[i] : "0";
            String str2 = i < nums2.length ? nums2[i] : "0";
            long num1 = 0;
            //字符串转数字
            for(int j = 0; j < str1.length(); j++)
                num1 = num1 * 10 + (str1.charAt(j) - '0');
            long num2 = 0;
            for(int j = 0; j < str2.length(); j++)
                num2 = num2 * 10 + (str2.charAt(j) - '0');
            //比较数字大小
            if(num1 > num2)
                return 1;
            if(num1 < num2)
                return -1;
        }
        //版本相同
        return 0;
    }
}
