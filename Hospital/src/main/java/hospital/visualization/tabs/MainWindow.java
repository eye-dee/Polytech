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
    private final JTabbedPane tabbedPane;

    public MainWindow(
            final TreeRepresentation treeRepresentation,
            final LoginWindow loginWindow,
            final JTabbedPane tabbedPane
    ) {
        super(new GridLayout(1, 0));

        this.tabbedPane = tabbedPane;
        loginWindow.setjTabbedPane(tabbedPane);

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
