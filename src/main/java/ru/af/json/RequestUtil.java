package ru.af.json;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import ru.af.formatvacancy.PropertyHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Получение вакансий в форамате json из API HH и
 * их перобразование для дальнейших действий
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


    /**
     * Получиь json с вакансиями
     *
     * @param page  страница
     * @param query поисковой запрос
     * @return переведенная в строку из json вакакнсия
     */

    static String getVacancies(int page, final String query) {
        try {
            URI uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("api.hh.ru")
                    .setPath(VACANCIES_PATH)
                    .setParameter(TEXT_PARAM, query)
                    .setParameter(PAGE_PARAM, page + "")
                    .setParameter(AREA_PARAM, SPB_CODE)
                    .setParameter(EXPERIENCE_PARAM, PropertyHolder.getInstance().EXPIRIENCE)
                    .build();
            HttpGet getRequest = new HttpGet(uri);
            getRequest.addHeader("accept", "application/json");

            return getJson(getRequest);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Получить json запрошенного через getRequest
     *
     * @param getRequest get-запрос для полукчение вакансии в формате json
     * @return конвертированный json в объект string
     */
    private static String getJson(HttpGet getRequest) {
        HttpClientBuilder builder = HttpClientBuilder.create();

        CloseableHttpClient httpClient = builder.build();
        try {
            HttpResponse response = httpClient.execute(getRequest);

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
}