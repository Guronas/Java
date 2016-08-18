package com.netcracker.edu.java.tasks;
/**
 * Created by Frolov on 23.03.2016.
 */
public class Maintest {

    class A {
        int x =1;
    }
    class B extends A {
        int x = 2;
    }

    void f(){
        A b = new B();
        System.out.println(b.x);
    }

    public static void main(String[] args) {
        TreeNode child = new TreeNodeImpl();
        TreeNode newTree = new TreeNodeImpl();
        TreeNode rootTree = new TreeNodeImpl();
        rootTree.addChild(newTree);
        newTree.addChild(child);
        rootTree.setData("jopa");
        System.out.println(newTree.findParent("jopa"));
        System.out.println(child.findParent(new Integer(2)));
    }
}
