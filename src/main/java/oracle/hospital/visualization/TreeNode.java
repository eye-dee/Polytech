package oracle.hospital.visualization;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */

public class TreeNode {
    private TreeNode parent;
    private final List<TreeNode> childs = new ArrayList<>();

    private final String name;

    public TreeNode(final String name) {
        this.name = name;
    }

    public void add(final TreeNode child) {
        childs.add(child);
    }

    public void addAll(final TreeNode ... childs) {
        for (final TreeNode child : childs) {
            this.childs.add(child);
        }
    }

    public void addAll(final List<TreeNode> childs) {
        this.childs.addAll(childs);
    }

    public TreeNode getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public TreeNode getChildAt(final int i) {
        return childs.get(i);
    }

    public int size() {
        return childs.size();
    }

    public int getIndexOfChild(final TreeNode treeNode) {
        return childs.indexOf(treeNode);
    }
}
