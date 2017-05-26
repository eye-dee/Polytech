package hospital.visualization.graph;

import hospital.types.Departure;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Polytech
 * Created on 27.05.17.
 */
public class PeopleGraph {
    public PeopleGraph(final Map<Long, Departure> departureMap) {
        final SimpleDrawerImprove simpleDrawerImprove = new SimpleDrawerImprove("Количество людей");

        simpleDrawerImprove.addChartPanelWithDefaultX(
                departureMap.values()
                        .stream()
                        .map(
                                departure ->
                                        departure.getWards()
                                                .stream()
                                                .mapToDouble(
                                                        ward -> ward.getPeoples().size()
                                                )
                                                .sum()
                        )
                        .collect(Collectors.toList())
        );

        simpleDrawerImprove.draw();
    }
}
