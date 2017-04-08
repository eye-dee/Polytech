package jfree; /**
 * Polytech
 * Created by Игорь on 23.02.2017.
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class FirstTry {
    public static void main(final String[] args) {
        final XYSeries series = new XYSeries("sin(a)");

        for(float i = 0; i < Math.PI; i+=0.1){
            series.add(i, Math.sin(i));
        }

        final XYDataset xyDataset = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory
                .createXYLineChart("y = sin(x)", "x", "y",
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
