package data;

import config.Configuration;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import static data.Transformer.fromJson;

class HttpUtil {

    static ResponseBody getResponseBody(Request request) {
        try {
            HttpGet getRequest = new HttpGet(requestToUrl(request));
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(getRequest)) {
                return fromJson(ResponseBody.class, EntityUtils.toString(response.getEntity()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String requestToUrl(Request request) {
        return
                "http://api.marketstack.com/v1/eod?" +
                        "access_key=" + Configuration.ACCESS_KEY +
                        "&symbols=" + request.symbol() +
                        "&date_to=" + request.dateTo();
    }

}