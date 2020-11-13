package com.slt.netty.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建树形结构 ——  获取树形根菜单 —— 根据树形根菜单获取子菜单（递归调用）
 */
public class Tree {
    List<TreeNode> nodes = new ArrayList<TreeNode>();

    public Tree(List<TreeNode> nodes) {
        super();
        this.nodes = nodes;
    }

    /**
     * 构建树形结构
     *
     * @return
     */
    public List<TreeNode> buildTree() {
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        //获取根节点
        List<TreeNode> rootNodes = getRootNodes();
        //遍历根节点
        for (TreeNode rootNode : rootNodes) {
            //构建子节点
            buildChildNodes(rootNode);
            //构建完毕以后就是一颗一颗的树了
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    /**
     * 递归子节点
     * @param node
     */
    public void buildChildNodes(TreeNode node) {
        List<TreeNode> children = getChildNodes(node);
        if (!children.isEmpty()) {
            for (TreeNode child : children) {
                //压栈的点，弹栈的时候也是返回到这里
                buildChildNodes(child);
            }
            node.setChildren(children);
        }
    }

    /**
     * 获取父节点下所有的子节点
     * @param pnode
     * @return
     */
    public List<TreeNode> getChildNodes(TreeNode pnode) {
        List<TreeNode> childNodes = new ArrayList<TreeNode>();
        for (TreeNode n : nodes) {
            if (pnode.getId().equals(n.getParentId())) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }

    /**
     * 判断是否为根节点
     * @return
     */
    public boolean rootNode(TreeNode node) {
        boolean isRootNode = true;
        for (TreeNode n : nodes) {
            if(node.getParentId().equals(n.getId())){
                isRootNode = false;
                break;
            }
        }
        return isRootNode;
    }

    /**
     * 获取集合中所有的根节点
     * @return
     */
    public List<TreeNode> getRootNodes() {
        List<TreeNode> rootNodes = new ArrayList<TreeNode>();
        //遍历所有的数据节点
        for (TreeNode n : nodes) {
            if (rootNode(n)) {
                rootNodes.add(n);
            }
        }
        return rootNodes;
    }
}
