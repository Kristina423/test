package personal.solution.triso;

import java.util.Scanner;


public class Main {
    final public static int Maxsize = 100;
    final public static int Null = -1;
    public static int[] check = new int[Main.Maxsize];//是否有结点指向的标志数组，check=1，表示有
    public static treenode[] t0 = new treenode[Maxsize];
    public static treenode[] t1 = new treenode[Maxsize];

    public static void main(String[] args) {
        //1.读入并创建所有树节点,并寻找根结点


        //结构数组实现链表数据结构（无空间浪费，但增添和删除元素与线性表相同，所以也称为静态链表）

        //根结点下标
        int root0 = -1;
        int root1 = -1;


        Scanner sc = new Scanner(System.in);
        int count0 = sc.nextInt();
        for (int i = 0; i < count0; i++) {
            t0[i] = new treenode();
            t0[i].set(sc.next().charAt(0), sc.next().charAt(0), sc.next().charAt(0));
        }//创建树0


        //遍历check，若check！=1,代表没有任何结点指向它，则该结点为根结点
        //寻找树0的根结点
        if (count0 != 0) {
            for (int i = 0; i < Maxsize; i++) {
                if (check[i] != 1) {
                    root0 = i;
                    break;
                }
            }
        }

        for (int i = 0; i < count0; i++) check[i] = 0;//清零便于后面使用

        int count1 = sc.nextInt();
        for (int i = 0; i < count1; i++) {
            t1[i] = new treenode();
            t1[i].set(sc.next().charAt(0), sc.next().charAt(0), sc.next().charAt(0));
        }//创建树1


        //寻找树1的根结点
        if (count1 != 0) {
            for (int i = 0; i < Maxsize; i++) {
                if (check[i] != 1) {
                    root1 = i;
                    break;
                }
            }
        }


        //3.比较是否同构
        if (Solution.Is(root0, root1)) System.out.println("Yes");
        else System.out.println("No");

        sc.close();
    }
}


class treenode {
    char data;//节点数据
    int left;//左儿子的下标
    int right;//右儿子的下标


    public void set(char d, char l, char r) {
        data = d;
        if (l != '-') {
            left = l - '0';
            Main.check[left] = 1;
        } else left = Main.Null;
        if (r != '-') {
            right = r - '0';
            Main.check[right] = 1;
        } else right = Main.Null;
    } //均以字符形式读入，读到‘-’代表没有儿子，将其赋值为空,否则转为整数赋值
}

//递归方法
class Solution {
    public static boolean Is(int root0, int root1) {
        if (root0 == -1 && root1 == -1) return true;//最小条件1,均为空

        //if((root0!=-1&&root1==-1)||(root1!=-1&&root0==-1))return false;//最小条件2，一个为空


        if (root0 != -1 && root1 != -1) {//均不为空
            if (Main.t0[root0].data != Main.t1[root1].data) return false;//且根结点相同，则则继续比较左右子树是否相同
            if (Main.t0[root0].left == -1 && Main.t1[root1].left == -1)//左子树均为空则比较右子树
                return Is(Main.t0[root0].right, Main.t1[root1].right);
            if (Main.t0[root0].left != -1 && Main.t1[root1].left != -1
                    && Main.t0[Main.t0[root0].left].data == Main.t1[Main.t1[root1].left].data)//左子树均不空,且根结点相同(如果不加该判断，则同构两树一定同构，不同构两树不一定不同构，因为可以交换左右子树位置)，则不需要交换左右子树，直接继续比较
                return Is(Main.t0[root0].left, Main.t1[root1].left) && Is(Main.t0[root0].right, Main.t1[root1].right);


            //否则左和右比较，右和左比较（此时左左一定不同构，所以这次比较后仍不同构，结果一定是不同构的，可以直接返回结果）
            return Is(Main.t0[root0].left, Main.t1[root1].right) && Is(Main.t0[root0].right, Main.t1[root1].left);
        }
        return false;
    }
}


