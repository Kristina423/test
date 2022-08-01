package personal.solution.leaves;


import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();

        //读入
        sl.BuildTree();

        //输出
        sl.LevelOrderTraversal();

        //关流
        Solution.sc.close();
        //push test
    }
}

class Solution {
    public static final int Null = -1;
    public static final int Maxsize = 100;
    public static Scanner sc = new Scanner(System.in);
    int[] check = new int[Maxsize];
    treenode[] tree = new treenode[Maxsize];//准备建立的树


    class treenode {
        int left;//左儿子的下标
        int right;//右儿子的下标

        void set(char l, char r) {
            if (l != '-') {
                left = l - '0';
                check[left] = 1;
            } else left = Null;
            if (r != '-') {
                right = r - '0';
                check[right] = 1;
            } else right = Null;
        } //均以字符形式读入，读到‘-’代表没有儿子，将其赋值为空,否则转为整数赋值
    }

    //根据输入建立树
    void BuildTree() {
        int count = sc.nextInt();
        for (int i = 0; i < count; i++) {
            tree[i] = new treenode();
            tree[i].set(sc.next().charAt(0), sc.next().charAt(0));
        }
    }

    //找树的根结点
    int FindRoot() {
        int root = Null;
        for (int i = 0; i < Maxsize; i++) {
            if (check[i] == 0) {
                root = i;
                break;
            }
        }

        return root;
    }


    //层序遍历并输出叶子结点
    void LevelOrderTraversal() {
        Deque<Integer> Q = new LinkedList<Integer>();
        //找到根结点,并入队
        int root = FindRoot();
        Q.addLast(root);


        //每次将队头出队，子节点入队
        boolean flag = true;//设置输出格式用
        while (!Q.isEmpty()) {
            int current = Q.pollFirst();
            if (tree[current].left == Null && tree[current].right == Null) {
                if (flag) flag = false;
                else System.out.print(' ');
                System.out.print(current);//左右儿子都为空，则为叶子结点，输出
                continue;
            }
            if(tree[current].left != Null) {
                Q.addLast(tree[current].left);
            }
            if(tree[current].right != Null) {
                Q.addLast(tree[current].right);
            }
        }
    }

}






