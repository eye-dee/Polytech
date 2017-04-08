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
 * Created by Игорь on 23.02.2017.
 */
public class SimpleDrawer implements Drawer {
    private final String name;
    private List<Double> y;
    private List<Double> x;

    public SimpleDrawer(final String name){
        this.name = name;
    }

    public void setX(final List<Double> x) {
        this.x = x;
    }

    public void createDefaultX(){
        x = new ArrayList<>();

        for (int i = 0; i < y.size(); ++i){
            x.add((double)i);
        }
    }

    public void setY(final List<Double> y) {
        this.y = y;
    }

    @Override
    public void draw(){
        final XYSeries xySeries = new XYSeries(name);
        for(int i = 0; i < x.size(); ++i){
            xySeries.add(x.get(i), y.get(i));
        }

        final XYDataset xyDataset = new XYSeriesCollection(xySeries);
        final JFreeChart chart = ChartFactory
                .createXYLineChart(name, "x", "k",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        final JFrame frame =
                new JFrame("MinimalStaticChart");
        // Помещаем график на фрейм
        frame.getContentPane()
                .add(new ChartPanel(chart));
        frame.setSize(400,300);
        frame.show();
    }
}
