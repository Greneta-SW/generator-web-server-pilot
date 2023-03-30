package com.greneta.generatorwebserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greneta.generatorwebserver.dto.GeneratorRequestDto;
import com.greneta.generatorwebserver.dto.HttpResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class HttpClientService {

    final String BASE_URL = "http://localhost:8081";
    final ObjectMapper mapper = new ObjectMapper();
    public HttpResponseDto generateModelPostRequest(GeneratorRequestDto dto) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(BASE_URL+"/blender/api");
            httpPost.setEntity(new StringEntity(mapper.writeValueAsString(dto)));
            httpPost.setHeader("Content-type", "application/json");
            log.info("Executing request = {} ",httpPost.getRequestLine());
            return httpclient.execute(httpPost, getResponseHandler());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpResponseDto deleteModelRequest(Long modelId) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpDelete httpPost = new HttpDelete(BASE_URL+"/blender/api/"+modelId);
            httpPost.setHeader("Content-type", "application/json");
            log.info("Executing request = {} ",httpPost.getRequestLine());
            return httpclient.execute(httpPost, getResponseHandler());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ResponseHandler<HttpResponseDto> getResponseHandler() {
        return response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity responseBody = response.getEntity();
                if (responseBody != null) {
                    return mapper.readValue(
                            EntityUtils.toString(responseBody),
                            HttpResponseDto.class
                    );
                } else {
                    return null;
                }
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
    }

}
