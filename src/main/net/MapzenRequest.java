package main.net;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by dimitris on 4/3/16.
 */
public class MapzenRequest {

    private CloseableHttpClient httpClient;
    private HttpGet httpget;
    private CloseableHttpResponse response;
    private String entity;
    private int statusCode;
    public MapzenRequest(String url) {
        System.out.println(url);
        this.httpClient = HttpClients.createDefault();
        httpget = new HttpGet(url.toString());
        try {
            response = httpClient.execute(httpget);
            entity = (EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new Error("Http Response status code: " + statusCode);
        }

    }//Constructor


    public String getContent() {
        return this.entity;
    }

    public int getStatusCode(){
        return this.statusCode;
    }

}
