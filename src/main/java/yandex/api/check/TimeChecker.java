package yandex.api.check;

import lombok.AllArgsConstructor;
import lombok.Data;
import statistic.modeling.lab1.SimpleDrawerImprove;
import yandex.HttpUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import static java.time.LocalDateTime.now;

/**
 * Polytech
 * Created on 22.05.17.
 */
public class TimeChecker {
    private static final Logger LOGGER = Logger.getLogger(TimeChecker.class.getName());
    static {
        try {
            Arrays.stream(LOGGER.getHandlers()).forEach(LOGGER::removeHandler);
            LOGGER.setUseParentHandlers(false);
            LOGGER.addHandler(new FileHandler(LOGGER.getName()));

        } catch (final IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static final int DAY_AMOUNT = 1500;
    private static final int QUERY_COUNT = 10;

    private static final String QUERY1 =
            "https://api-metrika.yandex.ru//stat/v1/data?" +
                    "ids=34322390&" +
                    "oauth_token=AQAAAAAUQvf-AAQfH-ET9xpCKUwIr4MDS8a21lY&" +
                    "date1=%s&" +
                    "date2=%s&" +
                    "limit=10000&" +
                    "offset=1&" +
                    "metrics=ym:s:visits,ym:s:bounceRate,ym:s:pageDepth,ym:s:avgVisitDurationSeconds&" +
                    "dimensions=ym:s:lastDirectClickOrder,ym:s:lastDirectClickBanner,ym:s:lastDirectPhraseOrCond,ym:s:lastDirectSearchPhrase";

    @AllArgsConstructor
    @Data
    public static class Run implements Runnable {
        private AtomicBoolean aBoolean;

        @Override
        public void run() {
            while (true) {
                try {
                    final String cont = new String(Files.readAllBytes(Paths.get("cont")));
                    if (cont.equals("stop")) {
                        aBoolean.set(false);
                    }
                } catch ( final IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void main(final String[] args) throws IOException {
        final LocalDateTime now = now();
        final AtomicBoolean cont = new AtomicBoolean(true);

        final Thread thread = new Thread(new Run(cont));

        thread.start();

        final SimpleDrawerImprove simpleDrawerImprove = new SimpleDrawerImprove("Yandex Metrika");
        final List<Double> values = new ArrayList<>();

        for (int i = 0; i < DAY_AMOUNT; ++i) {
            double sumTime = 0.0;
            for (int j = 0; j < QUERY_COUNT; ++j) {
                final double startTime;
                final double endTime;

                startTime = System.nanoTime();
                final int response = HttpUtil.easyExec(
                        String.format(QUERY1, now.toString(), now.minusDays(i).toString()),
                        HttpUtil.GET
                );
                endTime = System.nanoTime();

                sumTime += endTime - startTime;

                LOGGER.info("response code = " + response);
            }

            values.add(sumTime);
            System.out.println(i + " : " + sumTime);
            if (!cont.get()) {
                break;
            }
        }

        simpleDrawerImprove.addChartPanelWithDefaultX(values);

        simpleDrawerImprove.draw();
    }
}
