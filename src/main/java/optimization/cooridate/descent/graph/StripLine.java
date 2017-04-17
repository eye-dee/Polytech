package optimization.cooridate.descent.graph;

import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static optimization.cooridate.descent.CGTemplate.CANVAS_HEIGHT;
import static optimization.cooridate.descent.CGTemplate.CANVAS_WIDTH;

/**
 * Polytech
 * Created by igor on 17.04.17.
 */
@Data
public class StripLine {
    private List<Pair<Integer, Integer>> line = new ArrayList<>();

    private Color color;

    public StripLine(final List<Pair<Double, Double>> pairs) {
        pairs.stream()
                .map(
                        doubleDoublePair ->
                                Pair.of(
                                        doubleDoublePair.getLeft() * 100,
                                        doubleDoublePair.getRight() * 100
                                )
                )
                .map(
                        doubleDoublePair ->
                                Pair.of(
                                        doubleDoublePair.getLeft().intValue(),
                                        doubleDoublePair.getRight().intValue()
                                )
                )
                .forEach(line::add);
    }

    public void addAll(final List<Pair<Integer, Integer>> list) {
        line.addAll(list);
    }

    public void addPair(final int x, final int y) {
        line.add(Pair.of(x, y));
    }

    public void addPair(final Pair p) {
        line.add(p);
    }

    public void draw(final Graphics graphics) {
        graphics.setColor(color);

        for (int i = 1; i < line.size(); ++i) {
            final Pair<Integer, Integer> p0 = line.get(i - 1);
            final Pair<Integer, Integer> p1 = line.get(i);

            graphics.drawLine(
                    p0.getLeft(), p0.getRight(),
                    p1.getLeft(), p1.getRight()
            );
        }
    }

    public List<Integer> getX() {
        return line.stream()
                .map(Pair::getLeft)
                .collect(Collectors.toList());
    }

    public List<Integer> getY() {
        return line.stream()
                .map(Pair::getRight)
                .collect(Collectors.toList());
    }

    public void map(final Integer minX, final Integer maxX, final Integer minY, final Integer maxY) {
        line = line.stream()
                .map(integerIntegerPair ->
                        Pair.of(
                                CANVAS_WIDTH * (integerIntegerPair.getLeft() - minX) / (maxX - minX),
                                CANVAS_HEIGHT * (integerIntegerPair.getRight() - minY) / (maxY - minY)
                        )
                )
                .map(
                        integerIntegerPair ->
                                Pair.of(
                                        integerIntegerPair.getLeft(),
                                        CANVAS_HEIGHT - integerIntegerPair.getRight()
                                )
                )
                .collect(Collectors.toList());
    }
}
