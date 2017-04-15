package hospital.visualization;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */
public class HospitalTree extends JTree {
    HospitalTreeModel hospitalTreeModel;

    public HospitalTree(final TreeNode root) {
        super(new HospitalTreeModel(root));

        getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);
        final DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        final Icon personIcon = null;
        renderer.setLeafIcon(personIcon);
        renderer.setClosedIcon(personIcon);
        renderer.setOpenIcon(personIcon);
        setCellRenderer(renderer);
    }
}
