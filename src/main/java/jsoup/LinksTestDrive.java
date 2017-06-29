package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Polytech
 * Created on 22.06.17.
 */
public class LinksTestDrive {
    public static void main(final String[] args) throws IOException {
        final Document document = Jsoup.connect("https://yandex.ru/search/?text=%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81").get();
        final Elements links = document.select("h2");

        final Set<Element> collect = links.stream().collect(Collectors.toSet());

        for (int i = 0; i < 1000; ++i) {
            System.out.println(i);
            final Document doc = Jsoup.connect("https://yandex.ru/search/?text=%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81").get();
            final Elements ls = doc.select("h2");

            if (ls.size() < 6) {
                System.out.println(ls.size());
                break;
            }
        }

        links.forEach(System.out::println);
    }
}
