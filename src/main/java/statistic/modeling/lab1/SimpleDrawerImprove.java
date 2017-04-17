package statistic.modeling.lab1;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Polytech
 * Created by igor on 17.04.17.
 */
public class SimpleDrawerImprove implements Drawer {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private final String name;
    private final JFrame frame =
            new JFrame("MinimalStaticChart");

    public SimpleDrawerImprove(final String name) {
        this.name = name;
    }

    public void addChartPanelWithDefaultX(final List<Double> y) {
        final List<Double> x = new ArrayList<>();

        for (int i = 0; i < y.size(); ++i) {
            x.add((double) i);
        }

        addChartPanel(x, y);
    }

    public void addChartPanel(final List<Double> x, final List<Double> y) {
        final XYSeries xySeries = new XYSeries(name);
        for (int i = 0; i < x.size(); ++i) {
            xySeries.add(x.get(i), y.get(i));
        }

        final XYDataset xyDataset = new XYSeriesCollection(xySeries);
        final JFreeChart chart = ChartFactory
                .createXYLineChart(name, "x", "k",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        // Помещаем график на фрейм
        frame.getContentPane()
                .add(new ChartPanel(chart));
    }

    @Override
    public void draw() {
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
    }
}
