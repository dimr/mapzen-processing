package net;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dimitris on 4/3/16.
 */
public class MapzenRequest {

    private CloseableHttpClient httpClient;
    private HttpGet httpget;
    private CloseableHttpResponse response;
    private String entity;

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
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new Error("Http Response status code: " + statusCode);
        }

    }//Constructor


    public String getContent() {
        return this.entity;
    }

}
