package nowcoder.shuangzhizhen;

import java.util.Arrays;

//合并两个有序的数组（插入法，从大数组末尾到头）
public class BM87 {
    //使用辅助数组，避免频繁移动数组中的元素
    public void merge(int A[], int m, int B[], int n) {
        if (n == 0) {
            return;
        }
        int[] temp = Arrays.copyOf(A, m);
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < m && j < n) {
            if (temp[i] < B[j]) {
                A[index] = temp[i];
                i++;
            } else {
                A[index] = B[j];
                j++;
            }
            index++;
        }

        while (i < m) {
            A[index] = temp[i];
            i++;
            index++;
        }
        while(j<n){
            A[index] = B[j];
            j++;
            index++;
        }
    }


    //从大数组的末尾开始往前插入，就不会覆盖之前数组中的数
    public void merge2(int A[], int m, int B[], int n) {
        int a = m-1;
        int b = n-1;
        for(int i = m+n-1 ; i >= 0 ; i--)//需要填m+n次
        {
            if(b<0||(a>=0&&A[a]>=B[b]))
            //B数组中的数全部用完了就填A数组中的数 a数组中的数没有用完，并且A数组的数大
            {
                A[i]=A[a];
                a--;
            }
            else
            {
                A[i]=B[b];
                b--;
            }
        }
    }
}
