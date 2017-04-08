package yandex.loggers;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Polytech
 * Created by igor on 03.04.17.
 */
public class YandexLogger {
    private static final String TOKEN = "AQAAAAAUQvf-AAQfH-ET9xpCKUwIr4MDS8a21lY";
    private static final String COUNTER_ID = "34322390";

    private static final String GET = "GET";
    private static final String POST = "POST";

    //must be POST
    private static final String CREATE_LOGGER_TEMPLATE = "https://api-metrika.yandex.ru/management/v1/counter/%s/logrequests?" +
            "oauth_token=%s" +
            "&date1=%s" +
            "&date2=%s" +
            "&fields=%s" +
            "&source=%s";

    //GET
    private static final String GET_LOGGER_LIST = "https://api-metrika.yandex.ru/management/v1/counter/%s/logrequests?" +
            "oauth_token=%s";
    //POST
    private static final String CANCEL_LOGGER = "https://api-metrika.yandex.ru/management/v1/counter/%s/logrequest/%s/cancel?" +
            "oauth_token=%s";

    //GET
    private static final String ESTIMATE_REQUEST = "https://api-metrika.yandex.ru/management/v1/counter/%s/logrequests/evaluate?" +
            "oauth_token=%s" +
            "&date1=%s" +
            "&date2=%s" +
            "&fields=%s" +
            "&source=%s";

    //GET
    private static final String INFORMATION_REQUEST = "https://api-metrika.yandex.ru/management/v1/counter/%s/logrequest/%s?" +
            "oauth_token=%s";

    //GET
    private static final String DOWNLOAD_LOGGER_PART = "https://api-metrika.yandex.ru/management/v1/counter/%s/logrequest/%s/part/%s/download?" +
            "oauth_token=%s";

    //POST
    private static final String CLEAN_LOGGER = "https://api-metrika.yandex.ru/management/v1/counter/%s/logrequest/%s/clean?" +
            "oauth_token=%s";

    private final List<String> loggerIds = new ArrayList<>();
    private int currentLogger = 0;

    private final List<String> possibleHits = new ArrayList<>();
    private final List<String> possibleVisits = new ArrayList<>();
    {
        loggerIds.add("74029");

        possibleHits.add("ym:pv:watchID");
        possibleHits.add("ym:pv:counterID");
        possibleHits.add("ym:pv:date");
        possibleHits.add("ym:pv:dateTime");
        possibleHits.add("ym:pv:title");
        possibleHits.add("ym:pv:URL");
        possibleHits.add("ym:pv:referer");
        possibleHits.add("ym:pv:UTMCampaign");
        possibleHits.add("ym:pv:UTMContent");
        possibleHits.add("ym:pv:UTMMedium");
        possibleHits.add("ym:pv:UTMSource");
        possibleHits.add("ym:pv:UTMTerm");
        possibleHits.add("ym:pv:browser");
        possibleHits.add("ym:pv:browserMajorVersion");
        possibleHits.add("ym:pv:browserMinorVersion");
        possibleHits.add("ym:pv:browserCountry");
        possibleHits.add("ym:pv:browserEngine");
        possibleHits.add("ym:pv:browserEngineVersion1");
        possibleHits.add("ym:pv:browserEngineVersion2");
        possibleHits.add("ym:pv:browserEngineVersion3");
        possibleHits.add("ym:pv:browserEngineVersion4");
        possibleHits.add("ym:pv:browserLanguage");
        possibleHits.add("ym:pv:clientTimeZone");
        possibleHits.add("ym:pv:cookieEnabled");
        possibleHits.add("ym:pv:deviceCategory");
        possibleHits.add("ym:pv:flashMajor");
        possibleHits.add("ym:pv:flashMinor");
        possibleHits.add("ym:pv:from");
        possibleHits.add("ym:pv:hasGCLID");
        possibleHits.add("ym:pv:ipAddress");
        possibleHits.add("ym:pv:javascriptEnabled");
        possibleHits.add("ym:pv:mobilePhone");
        possibleHits.add("ym:pv:mobilePhoneModel");
        possibleHits.add("ym:pv:openstatAd");
        possibleHits.add("ym:pv:openstatCampaign");
        possibleHits.add("ym:pv:openstatService");
        possibleHits.add("ym:pv:openstatSource");
        possibleHits.add("ym:pv:operatingSystem");
        possibleHits.add("ym:pv:operatingSystemRoot");
        possibleHits.add("ym:pv:physicalScreenHeight");
        possibleHits.add("ym:pv:physicalScreenWidth");
        possibleHits.add("ym:pv:regionCity");
        possibleHits.add("ym:pv:regionCountry");
        possibleHits.add("ym:pv:screenColors");
        possibleHits.add("ym:pv:screenFormat");
        possibleHits.add("ym:pv:screenHeight");
        possibleHits.add("ym:pv:screenOrientation");
        possibleHits.add("ym:pv:screenWidth");
        possibleHits.add("ym:pv:windowClientHeight");
        possibleHits.add("ym:pv:windowClientWidth");
        possibleHits.add("ym:pv:params");
        possibleHits.add("ym:pv:lastTrafficSource");
        possibleHits.add("ym:pv:lastSearchEngine");
        possibleHits.add("ym:pv:lastSearchEngineRoot");
        possibleHits.add("ym:pv:lastAdvEngine");
        possibleHits.add("ym:pv:artificial");
        possibleHits.add("ym:pv:pageCharset");
        possibleHits.add("ym:pv:link");
        possibleHits.add("ym:pv:download");
        possibleHits.add("ym:pv:notBounce");
        possibleHits.add("ym:pv:lastSocialNetwork");
        possibleHits.add("ym:pv:httpError");
        possibleHits.add("ym:pv:clientID");
        possibleHits.add("ym:pv:networkType");
        possibleHits.add("ym:pv:lastSocialNetworkProfile");
        possibleHits.add("ym:pv:goalsID");
        possibleHits.add("ym:pv:shareService");
        possibleHits.add("ym:pv:shareURL");
        possibleHits.add("ym:pv:shareTitle");
        possibleHits.add("ym:pv:iFrame");

        possibleVisits.add("ym:s:visitID");
        possibleVisits.add("ym:s:counterID");
        possibleVisits.add("ym:s:watchIDs");
        possibleVisits.add("ym:s:date");
        possibleVisits.add("ym:s:dateTime");
        possibleVisits.add("ym:s:dateTimeUTC");
        possibleVisits.add("ym:s:isNewUser");
        possibleVisits.add("ym:s:startURL");
        possibleVisits.add("ym:s:endURL");
        possibleVisits.add("ym:s:pageViews");
        possibleVisits.add("ym:s:visitDuration");
        possibleVisits.add("ym:s:bounce");
        possibleVisits.add("ym:s:ipAddress");
        possibleVisits.add("ym:s:params");
        possibleVisits.add("ym:s:goalsID");
        possibleVisits.add("ym:s:goalsSerialNumber");
        possibleVisits.add("ym:s:goalsDateTime");
        possibleVisits.add("ym:s:goalsPrice");
        possibleVisits.add("ym:s:goalsOrder");
        possibleVisits.add("ym:s:goalsCurrency");
        possibleVisits.add("ym:s:clientID");
        possibleVisits.add("ym:s:lastTrafficSource");
        possibleVisits.add("ym:s:lastAdvEngine");
        possibleVisits.add("ym:s:lastReferalSource");
        possibleVisits.add("ym:s:lastSearchEngineRoot");
        possibleVisits.add("ym:s:lastSearchEngine");
        possibleVisits.add("ym:s:lastSocialNetwork");
        possibleVisits.add("ym:s:lastSocialNetworkProfile");
        possibleVisits.add("ym:s:referer");
        possibleVisits.add("ym:s:lastDirectClickOrder");
        possibleVisits.add("ym:s:lastDirectBannerGroup");
        possibleVisits.add("ym:s:lastDirectClickBanner");
        possibleVisits.add("ym:s:lastDirectPhraseOrCond");
        possibleVisits.add("ym:s:lastDirectPlatformType");
        possibleVisits.add("ym:s:lastDirectPlatform");
        possibleVisits.add("ym:s:lastDirectConditionType");
        possibleVisits.add("ym:s:lastCurrencyID");
        possibleVisits.add("ym:s:from");
        possibleVisits.add("ym:s:UTMCampaign");
        possibleVisits.add("ym:s:UTMContent");
        possibleVisits.add("ym:s:UTMMedium");
        possibleVisits.add("ym:s:UTMSource");
        possibleVisits.add("ym:s:UTMTerm");
        possibleVisits.add("ym:s:openstatAd");
        possibleVisits.add("ym:s:openstatCampaign");
        possibleVisits.add("ym:s:openstatService");
        possibleVisits.add("ym:s:openstatSource");
        possibleVisits.add("ym:s:hasGCLID");
        possibleVisits.add("ym:s:regionCountry");
        possibleVisits.add("ym:s:regionCity");
        possibleVisits.add("ym:s:browserLanguage");
        possibleVisits.add("ym:s:browserCountry");
        possibleVisits.add("ym:s:clientTimeZone");
        possibleVisits.add("ym:s:deviceCategory");
        possibleVisits.add("ym:s:mobilePhone");
        possibleVisits.add("ym:s:mobilePhoneModel");
        possibleVisits.add("ym:s:operatingSystemRoot");
        possibleVisits.add("ym:s:operatingSystem");
        possibleVisits.add("ym:s:browser");
        possibleVisits.add("ym:s:browserMajorVersion");
        possibleVisits.add("ym:s:browserMinorVersion");
        possibleVisits.add("ym:s:browserEngine");
        possibleVisits.add("ym:s:browserEngineVersion1");
        possibleVisits.add("ym:s:browserEngineVersion2");
        possibleVisits.add("ym:s:browserEngineVersion3");
        possibleVisits.add("ym:s:browserEngineVersion4");
        possibleVisits.add("ym:s:cookieEnabled");
        possibleVisits.add("ym:s:javascriptEnabled");
        possibleVisits.add("ym:s:flashMajor");
        possibleVisits.add("ym:s:flashMinor");
        possibleVisits.add("ym:s:screenFormat");
        possibleVisits.add("ym:s:screenColors");
        possibleVisits.add("ym:s:screenOrientation");
        possibleVisits.add("ym:s:screenWidth");
        possibleVisits.add("ym:s:screenHeight");
        possibleVisits.add("ym:s:physicalScreenWidth");
        possibleVisits.add("ym:s:physicalScreenHeight");
        possibleVisits.add("ym:s:windowClientWidth");
        possibleVisits.add("ym:s:windowClientHeight");
        possibleVisits.add("ym:s:purchaseID");
        possibleVisits.add("ym:s:purchaseDateTime");
        possibleVisits.add("ym:s:purchaseAffiliation");
        possibleVisits.add("ym:s:purchaseRevenue");
        possibleVisits.add("ym:s:purchaseTax");
        possibleVisits.add("ym:s:purchaseShipping");
        possibleVisits.add("ym:s:purchaseCoupon");
        possibleVisits.add("ym:s:purchaseCurrency");
        possibleVisits.add("ym:s:purchaseProductQuantity");
        possibleVisits.add("ym:s:productsPurchaseID");
        possibleVisits.add("ym:s:productsID");
        possibleVisits.add("ym:s:productsName");
        possibleVisits.add("ym:s:productsBrand");
        possibleVisits.add("ym:s:productsCategory");
        possibleVisits.add("ym:s:productsCategory1");
        possibleVisits.add("ym:s:productsCategory2");
        possibleVisits.add("ym:s:productsCategory3");
        possibleVisits.add("ym:s:productsCategory4");
        possibleVisits.add("ym:s:productsCategory5");
        possibleVisits.add("ym:s:productsVariant");
        possibleVisits.add("ym:s:productsPosition");
        possibleVisits.add("ym:s:productsPrice");
        possibleVisits.add("ym:s:productsCurrency");
        possibleVisits.add("ym:s:productsCoupon");
        possibleVisits.add("ym:s:productsQuantity");
        possibleVisits.add("ym:s:impressionsURL");
        possibleVisits.add("ym:s:impressionsDateTime");
        possibleVisits.add("ym:s:impressionsProductID");
        possibleVisits.add("ym:s:impressionsProductName");
        possibleVisits.add("ym:s:impressionsProductBrand");
        possibleVisits.add("ym:s:impressionsProductCategory");
        possibleVisits.add("ym:s:impressionsProductCategory1");
        possibleVisits.add("ym:s:impressionsProductCategory2");
        possibleVisits.add("ym:s:impressionsProductCategory3");
        possibleVisits.add("ym:s:impressionsProductCategory4");
        possibleVisits.add("ym:s:impressionsProductCategory5");
        possibleVisits.add("ym:s:impressionsProductVariant");
        possibleVisits.add("ym:s:impressionsProductPrice");
        possibleVisits.add("ym:s:impressionsProductCurrency");
        possibleVisits.add("ym:s:impressionsProductCoupon");
        possibleVisits.add("ym:s:lastDirectClickOrderName");
        possibleVisits.add("ym:s:lastClickBannerGroupName");
        possibleVisits.add("ym:s:lastDirectClickBannerName");
        possibleVisits.add("ym:s:networkType");
    }

    public int getDivide() {
        return possibleVisits.size();
    }

    private String executeRequest(final String url,final String method) {
        try {
            final URL obj = new URL(url);
            final HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod(method);

            final int responseCode = con.getResponseCode();
            System.out.println("\nSending '" + POST + "' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            final BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            final StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                response.append('\t');
            }
            in.close();

            System.out.println(response.toString());
            return response.toString();
        } catch (final IOException exception) {
            System.out.println(exception.getMessage());
        }
        return "";
    }

    public void createLogger() {
        System.out.println("create logger:");

        final String url = String.format(CREATE_LOGGER_TEMPLATE,
                COUNTER_ID,
                TOKEN,
                "2017-03-25",
                "2017-04-2",
                possibleVisits.subList(0,10).stream().collect(Collectors.joining(",")),
                VISITS);

        executeRequest(url,POST);
    }

    public void getLoggerList() {
        System.out.println("list loggers:");

        final String url = String.format(GET_LOGGER_LIST,
                COUNTER_ID,
                TOKEN);

        executeRequest(url,GET);
    }

    public void cancelLogger() {
        System.out.println("cancel logger:");
    }

    public void estimateRequest() {
        System.out.println("estimate logger:");

        final String url = String.format(ESTIMATE_REQUEST,
                COUNTER_ID,
                TOKEN,
                "2017-03-25",
                "2017-04-2",
                possibleVisits.stream().collect(Collectors.joining(",")),
                VISITS);

        executeRequest(url,GET);
    }

    public void informationRequest() {
        System.out.println("information:");
    }

    public String downloadLoggerPart() {
        System.out.println("download logger part:");

        final String url = String.format(DOWNLOAD_LOGGER_PART,
                COUNTER_ID,
                loggerIds.get(currentLogger),
                "0",
                TOKEN);

        return executeRequest(url,GET);
    }

    public void cleanLogger() {
        System.out.println("clean logger:");

        final String url = String.format(CLEAN_LOGGER,
                COUNTER_ID,
                loggerIds.get(0),
                TOKEN);

        executeRequest(url,POST);
    }

    public void nextLogger() {
        if (loggerIds.size() == 0) {
            System.out.println("Нет вообще логгеров");
        } else {
            if (currentLogger + 1 < loggerIds.size()) {
                currentLogger++;
            } else {
                currentLogger = 0;
            }
        }
    }

    public void prevLogger() {
        if (loggerIds.size() == 0) {
            System.out.println("Нет логгеров вообще");
        } else {
            if (currentLogger > 0) {
                currentLogger--;
            } else {
                currentLogger = loggerIds.size() - 1;
            }
        }
    }

    private static void stringToScv(final String information, final int divide) {
        try {
            final PrintWriter pw = new PrintWriter(new File("test.csv"));
            final StringBuilder sb = new StringBuilder();

            //final String replacedInformation = information.replace("\n", "\t");

            final List<String> list = Arrays.asList(information.split("\t"));

            for (int i = 0; i < list.size(); ++i) {
                sb.append(list.get(i));
                if ( i > 1 && (i+1) % divide == 0) {
                    sb.append('\n');
                } else {
                    sb.append(';');
                }
            }

            pw.write(sb.toString());
            pw.close();
            System.out.println("done!");
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(final String[] args) {
        final YandexLogger yandexLogger = new YandexLogger();

        final Scanner scanner = new Scanner(System.in);
        String command = "";

        while (!"end".equals(command)) {
            if (scanner.hasNext()) {
                command = scanner.next();
            }
            if ("create".equals(command)) {
                yandexLogger.createLogger();
            } else if ("get".equals(command)) {
                yandexLogger.getLoggerList();
            } else if ("cancel".equals(command)) {
                yandexLogger.cancelLogger();
            } else if ("estimate".equals(command)) {
                yandexLogger.estimateRequest();
            } else if ("inf".equals(command)) {
                yandexLogger.informationRequest();
            } else if ("download".equals(command)) {
                stringToScv(yandexLogger.downloadLoggerPart(),yandexLogger.getDivide());
                //yandexLogger.downloadLoggerPart();
            } else if ("clean".equals(command)) {
                yandexLogger.cleanLogger();
            } else if ("next".equals(command)) {
                yandexLogger.nextLogger();
            } else if ("prev".equals(command)) {
                yandexLogger.prevLogger();
            }
        }
    }
    private static final String HITS = "hits";
    private static final String VISITS = "visits";
}
