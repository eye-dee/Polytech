package optimization.cooridate.descent;

import optimization.cooridate.descent.graph.LineDrawer;

import javax.swing.*;
import java.awt.*;

/**
 * Polytech
 * Created by igor on 17.04.17.
 */

public class CGTemplate extends JFrame {
    public static final int CANVAS_WIDTH = 1250;
    public static final int CANVAS_HEIGHT = 700;
    private final LineDrawer lineDrawer;

    private final DrawCanvas canvas;

    public CGTemplate(final LineDrawer lineDrawer) {
        this.lineDrawer = lineDrawer;

        lineDrawer.toWindow();

        canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        final Container cp = getContentPane();
        cp.add(canvas);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setTitle("......");
        setVisible(true);
    }

    private class DrawCanvas extends JPanel {
        @Override
        public void paintComponent(final Graphics g) {
            super.paintComponent(g);
            setBackground(Color.BLACK);
            g.setColor(Color.BLUE);

            lineDrawer.draw(g);
        }
    }
}
