package personal.solution.reversing;

import java.util.Scanner;

public class Main {
    public static final int maxsize = 100000;

    public static void main(String[] args) {


        //1.用数组保存输入数据
        //下标：当前地址
        //data数组：数据
        //next数组:下一元素
        Scanner sc = new Scanner(System.in);
        int[] data = new int[maxsize];
        int[] next = new int[maxsize];
        int head = sc.nextInt();//首地址
        int N = sc.nextInt();//总数量
        int K = sc.nextInt();//每K个进行反转
        for (int i = 0; i < N; i++) {
            int index = sc.nextInt();
            data[index] = sc.nextInt();
            next[index] = sc.nextInt();
        }


        //2.创建另一数组sort按顺序存放当前链表的地址，再存放反转后的结果：地址的顺序改变

        //遍历链表
        int[] sort = new int[maxsize];
        int i1 = 0;
        while (head != -1) {
            sort[i1++] = head;
            head = next[head];
        }
        //反转链表
        int temp;
        for (int i = 0; i +K-1<N; i += K) {
            for (int j = 0; j < K / 2; j++) {
                temp = sort[i + j];
                sort[i + j] = sort[i + K - 1 - j];
                sort[i + K - 1 - j] = temp;    //交换位置
            }
        }

        //3.按sort数组的值输出链表，并不要求将新的链表保存
        for (int i = 0; i < N - 1; i++) {
            System.out.printf("%05d %d %05d\n", sort[i], data[sort[i]], sort[i+1]);
        }
        System.out.printf("%05d %d %d\n", sort[N-1], data[sort[N-1]], -1);
    }
}





















