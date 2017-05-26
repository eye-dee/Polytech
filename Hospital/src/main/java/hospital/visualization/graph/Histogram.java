package hospital.visualization.graph;

import hospital.types.Ward;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Map;

/**
 * Polytech
 * Created on 27.05.17.
 */
public class Histogram {
    public Histogram(final Map<Long, Ward> values) {
        final JFrame frame = new JFrame("Занятость палат");
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(new Graph(values)));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    protected class Graph extends JPanel {
        protected static final int MIN_BAR_WIDTH = 20;
        private final Map<Long, Ward> mapHistory;

        public Graph(final Map<Long, Ward> mapHistory) {
            this.mapHistory = mapHistory;
            final int width = (mapHistory.size() * MIN_BAR_WIDTH) + 11;
            final Dimension minSize = new Dimension(width, 128);
            final Dimension prefSize = new Dimension(width, 256);
            setMinimumSize(minSize);
            setPreferredSize(prefSize);
        }

        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);
            if (mapHistory != null) {
                final int xOffset = 5;
                final int yOffset = 5;
                final int width = getWidth() - 1 - (xOffset * 2);
                final int height = getHeight() - 1 - (yOffset * 2);
                final Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(Color.DARK_GRAY);
                g2d.drawRect(xOffset, yOffset, width, height);
                final int barWidth = Math.max(MIN_BAR_WIDTH,
                        (int) Math.floor((float) width
                                / (float) mapHistory.size()));
                System.out.println("width = " + width + "; size = "
                        + mapHistory.size() + "; barWidth = " + barWidth);

                final long maxValue = mapHistory.values().stream()
                        .mapToLong(ward->ward.getPeoples().size())
                        .max()
                        .orElseThrow(RuntimeException::new);

                int xPos = xOffset;
                for (final Long key : mapHistory.keySet()) {
                    final long value = mapHistory.get(key).getPeoples().size();
                    final long max = mapHistory.get(key).getMaxCount();

                    final long barHeight = Math.round(((float) value
                            / (float) maxValue) * height);

                    g2d.setColor(new Color(1.0f - (float)value/max, (float)value/max,0));

                    final long yPos = height + yOffset - barHeight;
                    final Rectangle2D bar = new Rectangle2D.Float(
                            xPos, yPos, barWidth, barHeight);
                    g2d.fill(bar);
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.draw(bar);

                    g2d.setColor(Color.BLACK);
                    g2d.drawString(mapHistory.get(key).getWardName() + 0.2*barWidth,xPos,0.9f*height);

                    xPos += barWidth;
                }
                g2d.dispose();
            }
        }
    }
}
