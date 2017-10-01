package ru.oav.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import ru.oav.util.PropertyHolder;

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
    //текстовое поле
    private static final String TEXT_PARAM = "text";
    //регион. Справочник с возможными значениями: /areas
    private static final String AREA_PARAM = "area";
    //опыт работы.
    private static final String EXPERIENCE_PARAM = "experience";
    //это код СПб в API HH
    private static final String SPB_CODE = "2";

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

    public static String getVacancies(final String query) {
        return getVacancies(0, query);
    }

    /**
     * Получиь вакансиии с указанием страницы
     *
     * @param page  страница
     * @param query поисковой запрос
     * @return
     */
    public static String getVacancies(int page, final String query) {

        try {
            URI uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("api.hh.ru")
                    .setPath(VACANCIES_PATH)
                    .setParameter(TEXT_PARAM, query)
                    .setParameter(PAGE_PARAM, page + "")
                    .setParameter(AREA_PARAM, SPB_CODE)
                    .setParameter(EXPERIENCE_PARAM, PropertyHolder.getInstance().getExperience().getCode())
                    .build();
            HttpGet getRequest = new HttpGet(uri);

            return getJson(getRequest);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    /**
     * Получение json запрошенного через getRequest
     *
     * @param getRequest
     * @return
     */
    private static String getJson(HttpGet getRequest) {
        HttpClientBuilder builder = HttpClientBuilder.create();

        CloseableHttpClient httpClient = builder.build();
        try {
            getRequest.addHeader("accept", "application/json");

            HttpResponse response = getResponse(httpClient, getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String result = "";
            String output;
            while ((output = br.readLine()) != null) {
                result += output;
            }
            System.out.println(result);
            httpClient.close();
            return result;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }


    protected static HttpResponse getResponse(CloseableHttpClient httpClient,
                                              HttpGet getRequest) throws IOException {
        return httpClient.execute(getRequest);

    }
}
