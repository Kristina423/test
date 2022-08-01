package personal.solution.polynomial;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //输入多项式
        node poly1 = new input().in();
        node poly2 = new input().in();
        System.in.close();
        //相加并存入新的多项式
        node sum = personal.solution.polynomial.sum.sum(poly1, poly2);

        //相乘


        //输出多项式
        output.out(sum);
    }
}

class node {
    private int a, x;
    private node next;

    public node(int a, int x) {
        this.a = a;
        this.x = x;
    }

    public node() {
    }

    public void setNext(node next) {
        this.next = next;
    }

    public node getNext() {
        return next;
    }

    public int getA() {
        return a;
    }

    public int getX() {
        return x;
    }
}

class input {
    private Scanner ins = new Scanner(System.in);
    public node in() {
        node head = new node();
        node rear = head;
        int count = ins.nextInt();
        for (int i = 0; i < count; i++) {
            node nd = new node(ins.nextInt(), ins.nextInt());
            rear.setNext(nd);
            rear = nd;

        }
        return head;
    }
}

class sum {
    public static node sum(node poly1, node poly2) {
        node sum = new node(); //空头节点
        node rear = sum;   //尾节点
        poly1=poly1.getNext();
        poly2=poly2.getNext();
        while (poly1!= null && poly2!= null) {
            if (poly1.getX() < poly2.getX()) {

                rear.setNext(new node(poly2.getA(), poly2.getX()));
                poly2 = poly2.getNext();
                rear = rear.getNext();
            } else if (poly1.getX() > poly2.getX()) {

                rear.setNext(new node(poly1.getA(), poly1.getX()));
                poly1 = poly1.getNext();
                rear = rear.getNext();
            } else if (poly2.getA() + poly1.getA() != 0) {
                rear.setNext(new node(poly2.getA() + poly1.getA(), poly2.getX()));
                rear = rear.getNext();

            }
            else {
                poly1 = poly1.getNext();
                poly2 = poly2.getNext();
            }
        }
        while (poly1 != null) {

            rear.setNext(new node(poly1.getA(), poly1.getX()));
            rear = rear.getNext();
            poly1 = poly1.getNext();
        }

        while (poly2 != null) {

            rear.setNext(new node(poly2.getA(), poly2.getX()));
            rear = rear.getNext();
            poly2 = poly2.getNext();
        }
        return sum;
    }


}


class output {
    public static void out(node head) {
        String s;
        if (head.getNext() == null) {
            System.out.println("0 0");
            return;
        }
        boolean flag = false;
        while (head.getNext() != null) {
            if (!flag) {
                flag = true;
            } else System.out.print(" ");
            head = head.getNext();
            s = String.format("%d %d", head.getA(), head.getX());
            System.out.print(s);

        }
    }
}


