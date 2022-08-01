package personal.solution.subsequence;

import java.util.*;
public class Test {
    public static void main(String[] args){
        int[] ints = new int[100000];
        Scanner scanner = new Scanner(System.in);
        int length=scanner.nextInt();
        for(int i=0;i<length;i++)
        {
            ints[i]=scanner.nextInt();
        }
        System.out.println(Online.findmax(length,ints));
    }
}

class Online {
    public static int findmax(int length,int[] sequence) {
        int maxsub = 0,sum=0;
        for (int i = 0; i < length; i++)//只需遍历一次所有元素，时间复杂度为O（n）
        {
            sum+=sequence[i];
            if(sum>maxsub)maxsub=sum;//和当前maxsub比较，大于则更新
            else if(sum<0)sum=0;//若序列和为负数则抛弃（不可能包含在最大子列和里面)
        }

        return maxsub;
    }
}



//递归思想，分而治之
class DivideAndCounquer {
    public static int findmax(int left, int right, int[] sequence) {
        //1.最小返回条件，只有1个数
        if (left == right) {
            return sequence[left] < 0 ? 0 : sequence[left];
        }
        //2.开始递归解决
        int middle = (left + right) / 2;
        int leftmax = findmax(left, middle, sequence);//找左边的最大子列和
        int rightmax = findmax(middle + 1, right, sequence);//找右边的最大子列和
        //找跨边界的最大子列和
        int leftsum = 0;
        int maxleft = 0;
        for (int i = middle; i >= left; i--) {
            leftsum += sequence[i];
            if (leftsum > maxleft) {
                maxleft = leftsum;
            }
        }//往左最大

        int rightsum = 0;
        int maxright = 0;
        for (int i = middle + 1; i <= right; i++) {
            rightsum += sequence[i];
            if (rightsum > maxright) {
                maxright = rightsum;
            }
        }//往右最大

        //相加为跨边界最大
        int maxspan = maxleft + maxright;
        int max = 0;
        if (leftmax > rightmax) {
            max = leftmax > maxspan ? leftmax : maxspan;
        } else {
            max = rightmax > maxspan ? rightmax : maxspan;
        }

        return Math.max(max,0);
    }
}
