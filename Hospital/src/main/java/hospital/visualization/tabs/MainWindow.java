package hospital.visualization.tabs;

import hospital.visualization.list.WriteOutPeople;
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
    private final WriteOutPeople writeOutPeople;

    public MainWindow(
            final TreeRepresentation treeRepresentation,
            final LoginWindow loginWindow,
            final JTabbedPane tabbedPane,
            final WriteOutPeople writeOutPeople) {
        super(new GridLayout(1, 0));

        this.tabbedPane = tabbedPane;
        this.writeOutPeople = writeOutPeople;

        writeOutPeople.setjTabbedPane(tabbedPane);
        loginWindow.setjTabbedPane(tabbedPane);
        treeRepresentation.setjTabbedPane(tabbedPane);

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
}
