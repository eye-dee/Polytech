package hospital.visualization.tabs;

import hospital.visualization.login.LoginWindow;
import hospital.visualization.tree.TreeRepresentation;

import javax.swing.*;
import java.awt.*;

/**
 * Polytech
 * Created by igor on 03.05.17.
 */
public class MainWindow extends JPanel {
    public MainWindow(
            final TreeRepresentation treeRepresentation,
            final LoginWindow loginWindow
    ) {
        super(new GridLayout(1, 0));

        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Логин", null, loginWindow, null);
        tabbedPane.addTab("Дерево Больницы", null, treeRepresentation, null);
        tabbedPane.addTab("Titled", null, null, null);
        tabbedPane.addTab("Compound", null, null, null);
        tabbedPane.setSelectedIndex(0);
        final String toolTip = "<html>Blue Wavy Line border art crew:<br>&nbsp;&nbsp;&nbsp;Bill Pauley<br>&nbsp;&nbsp;&nbsp;Cris St. Aubyn<br>&nbsp;&nbsp;&nbsp;Ben Wronsky<br>&nbsp;&nbsp;&nbsp;Nathan Walrath<br>&nbsp;&nbsp;&nbsp;Tommy Adams, special consultant</html>";
        tabbedPane.setToolTipTextAt(1, toolTip);

        add(tabbedPane);
    }

    public static void createAndShowGUI(final MainWindow mainWindow) {
        final JFrame frame = new JFrame("BorderDemo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainWindow.setOpaque(true); //content panes must be opaque
        frame.setContentPane(mainWindow);

        frame.pack();
        frame.setVisible(true);
    }

    /*public static void main(final String[] args) {
        javax.swing.SwingUtilities.invokeLater(MainWindow::createAndShowGUI);
    }*/
}
