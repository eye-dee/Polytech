package optimization.cooridate.descent.graph;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Polytech
 * Created by igor on 17.04.17.
 */
public class LineDrawer {
    private final List<StripLine> lineList = new ArrayList<>();
    private Integer minY,minX,maxY,maxX;

    public void draw(final Graphics graphics) {
        lineList.forEach(line -> line.draw(graphics));
    }

    public void addLine(final StripLine stripLine) {
        lineList.add(stripLine);
    }

    public void toWindow() {
        final List<Integer> xs = new ArrayList<>();
        final List<Integer> ys = new ArrayList<>();

        lineList.forEach(stripLine -> {
            xs.addAll(stripLine.getX());
            ys.addAll(stripLine.getY());
        });

        minX = min(xs);
        minY = min(ys);

        maxX = max(xs);
        maxY = max(ys);

        lineList.forEach(stripLine -> stripLine.map(minX,maxX,minY,maxY));
    }

    static private int max(final List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).max().orElseThrow(IllegalArgumentException::new);
    }

    static private int min(final List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).min().orElseThrow(IllegalArgumentException::new);
    }
}
