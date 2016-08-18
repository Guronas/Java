package com.netcracker.edu.java.tasks;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Frolov Maksim on 23.03.2016.
 */

public class TreeNodeImpl implements TreeNode {
    public TreeNodeImpl() {
    }

    private TreeNode parent;
    private ArrayList<TreeNode> children = new ArrayList<>();
    private String state = "collapsed";
    private Object data;

    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    @Override
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    @Override
    public TreeNode getRoot() {
        TreeNode root = getParent();
        if (root == null) {
            return null;
        } else {
            while (root.getParent() != null) {
                root = root.getParent();
            }
            return root;
        }
    }

    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public Iterator<TreeNode> getChildrenIterator() {
        return new Iterator<TreeNode>() {
            ArrayList<TreeNode> children = TreeNodeImpl.this.children;
            int index = -1;

            @Override
            public boolean hasNext() {
                if (index == -1)
                    return children.size() > 0;
                return index < children.size() - 1;
            }

            @Override
            public TreeNode next() {
                index++;
                return children.get(index);
            }

            @Override
            public void remove() {

            }
        };
    }

    @Override
    public void addChild(TreeNode child) {
        children.add(child);
        child.setParent(this);
    }

    @Override
    public boolean removeChild(TreeNode child) {
        if (children.contains(child)) {
            child.setParent(null);
            return children.remove(child);
        }
        return false;
    }

    @Override
    public boolean isExpanded() {
        return state.equals("expanded");
    }

    @Override
    public void setExpanded(boolean expanded) {
        Iterator<TreeNode> iterator = getChildrenIterator();
        if (expanded) {
            state = "expanded";
            while (iterator.hasNext()) {
                iterator.next().setExpanded(true);
            }
        } else {
            state = "expanded";
            while (iterator.hasNext()) {
                iterator.next().setExpanded(false);
            }
        }
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }


    @Override
    public String getTreePath() {
        TreeNode node = this;
        String treePath = node.getData() != null ? node.getData().toString() : "empty";
        while (node.getParent() != null) {
            node = node.getParent();
            treePath = (node.getData() != null ? node.getData().toString() : "empty") + "->" + treePath;
        }
        return treePath;
    }

    @Override
    public TreeNode findParent(Object data) {
        TreeNode node = this;
        while (node.getParent() != null) {
            if ((node.getParent().getData() == null && data == null) ||
                    (node.getParent().getData() != null && node.getParent().getData().equals(data))) {
                break;
            } else {
                node = node.getParent();
            }
        }
        return node.getParent() == null ? null : node;
    }

    @Override
    public TreeNode findChild(Object data) {
        Iterator<TreeNode> iterator = getChildrenIterator();
        while (iterator.hasNext()) {
            TreeNode node = iterator.next();
            if ((node.getData() == null && data == null) || (node.getData() != null && node.getData().equals(data))) {
                return node;
            } else if (!node.isLeaf()) {
                return node.findChild(data);
            }
        }
        return null;
    }
}
