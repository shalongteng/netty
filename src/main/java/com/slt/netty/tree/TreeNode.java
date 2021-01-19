package com.slt.netty.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 */

public class TreeNode {
    private Integer id;
    private String title;
    private Integer parentId;
    private List<TreeNode> children;

    public TreeNode(Integer id,Integer parentId){
        this.id = id;
        this.parentId = parentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
