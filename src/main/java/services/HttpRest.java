package services;

import com.google.gson.Gson;
import model.ObjectAbstractDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public abstract class HttpRest {

    private static final String URL_CONNECTION = "http://localhost:8080/project-api/";

    static String getRequestHttp(String requestURL) {
        String js = "";
        try {
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() != 200){
                throw new RuntimeException("code: " + connection.getResponseCode() + " erro Http");
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String output;

            while ((output = bufferedReader.readLine()) != null){
                js += output;
            }
            connection.disconnect();

        } catch (Exception e) {
            System.out.println(e);
        }
        return js;
    }

    public static String getJsonDb(String endUrl) {
        String fullURL = URL_CONNECTION.concat(endUrl);

        return getRequestHttp(fullURL);
    }

    public static String getJsonDb(String endUrl, String filter) {
        String fullURL = String.format("%s/%s/%s", URL_CONNECTION, endUrl, filter.replaceAll(" ", "%20"));
        System.out.println(fullURL);
        return getRequestHttp(fullURL);
    }

    public static <T extends ObjectAbstractDto> List<T> getJsonDb(Class<T[]> classDto, String endURL) {
        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(getJsonDb(endURL), classDto));
    }

}