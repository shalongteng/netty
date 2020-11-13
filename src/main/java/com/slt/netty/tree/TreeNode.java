package com.slt.netty.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 */
@Data
public class TreeNode {
    private Integer id;
    private String title;
    private Integer parentId;
    private List<TreeNode> children;

    public TreeNode(Integer id,Integer parentId){
        this.id = id;
        this.parentId = parentId;
    }
}
