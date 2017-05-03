package hospital.visualization.tree;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.Vector;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */
public class HospitalTreeModel implements TreeModel {
    private final Vector<TreeModelListener> treeModelListeners =
            new Vector<>();
    private final TreeNode root;

    public HospitalTreeModel(final TreeNode root) {
        this.root = root;
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(final Object parent, final int index) {
        final TreeNode p = (TreeNode)parent;
        return p.getChildAt(index);
    }

    @Override
    public int getChildCount(final Object parent) {
        final TreeNode treeNode = (TreeNode)parent;

        return treeNode.size();
    }

    @Override
    public boolean isLeaf(final Object node) {
        final TreeNode p = (TreeNode)node;
        return (p.size() == 0);
    }

    @Override
    public void valueForPathChanged(final TreePath path, final Object newValue) {
        System.out.println("*** valueForPathChanged : "
                + path + " --> " + newValue);
    }

    @Override
    public int getIndexOfChild(final Object parent, final Object child) {
        final TreeNode p = (TreeNode)parent;
        return p.getIndexOfChild((TreeNode)child);
    }

    @Override
    public void addTreeModelListener(final TreeModelListener l) {
        treeModelListeners.add(l);
    }

    @Override
    public void removeTreeModelListener(final TreeModelListener l) {
        treeModelListeners.remove(l);
    }
}
