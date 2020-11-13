package com.slt.netty.tree;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<TreeNode> treeNodeList = new ArrayList<>();
        TreeNode treeNode1 = new TreeNode(1, 0);

        TreeNode treeNode2 = new TreeNode(2, 1);
        TreeNode treeNode3 = new TreeNode(3, 1);

        TreeNode treeNode4 = new TreeNode(4, 2);
        TreeNode treeNode5 = new TreeNode(5, 2);
        TreeNode treeNode6 = new TreeNode(6, 3);
        TreeNode treeNode7 = new TreeNode(7, 3);
        treeNodeList.add(treeNode1);
        treeNodeList.add(treeNode2);
        treeNodeList.add(treeNode3);
        treeNodeList.add(treeNode4);
        treeNodeList.add(treeNode5);
        treeNodeList.add(treeNode6);
        treeNodeList.add(treeNode7);


        List<TreeNode> buildTree = new Tree(treeNodeList).buildTree();
        System.out.println(buildTree);
    }
}
