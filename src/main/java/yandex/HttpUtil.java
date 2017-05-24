package yandex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Polytech
 * Created on 22.05.17.
 */
public class HttpUtil {
    public static final String GET = "GET";
    public static final String POST = "POST";

    public static String executeRequest(final String url, final String method) {
        try {
            final URL obj = new URL(url);
            final HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod(method);

            final int responseCode = con.getResponseCode();
            System.out.println("\nSending '" + method + "' request to URL : " + url);
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

    public static int easyExec(final String url, final String method) throws IOException {
        final URL obj = new URL(url);
        final HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod(method);

        final int responseCode = con.getResponseCode();
        con.disconnect();

        return responseCode;
    }
}
