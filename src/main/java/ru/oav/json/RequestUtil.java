package ru.oav.json;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by antonorlov on 16/06/2017.
 */
class RequestUtil {

    private static final String VACANCIES_PATH = "vacancies";
    private static final String PAGE_PARAM = "page";
    private static final String TEXT_PARAM = "text";

    public static String getVacancy(final String id) {
        try {
            URI uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("api.hh.ru")
                    .setPath(VACANCIES_PATH + "/" + id)
                    .build();
            HttpGet getRequest = new HttpGet(uri);

            return getJson(getRequest);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getVacansies(final String query) {
        return getVacansies(0, query);
    }

    /**
     * Получиь json с вакансиями
     *
     * @param page  страница
     * @param query поисковой запрос
     * @return
     */
    public static String getVacansies(int page, final String query) {

        try {
            URI uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("api.hh.ru")
                    .setPath(VACANCIES_PATH)
                    .setParameter(TEXT_PARAM, query)
                    .setParameter(PAGE_PARAM, page + "")
                    .build();
            HttpGet getRequest = new HttpGet(uri);

            return getJson(getRequest);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    private static String getJson(HttpGet getRequest) {
        HttpClientBuilder builder = HttpClientBuilder.create();

        CloseableHttpClient httpClient = builder.build();
        try {

//            System.out.println("URL : " + getRequest.getURI());

            getRequest.addHeader("accept", "application/json");

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String result = "";
            String output;
//            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                result += output;
            }
//          System.out.println(result);
            httpClient.close();
            return result;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
