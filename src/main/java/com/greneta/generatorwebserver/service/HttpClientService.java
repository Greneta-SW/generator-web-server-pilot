package com.greneta.generatorwebserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greneta.generatorwebserver.constant.ApiUrl;
import com.greneta.generatorwebserver.constant.ErrorCode;
import com.greneta.generatorwebserver.dto.GeneratorRequestDto;
import com.greneta.generatorwebserver.dto.HttpResponseDto;
import com.greneta.generatorwebserver.exception.ServiceLogicException;
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

    public final String BASE_URI = "/blender/api";
    private final ObjectMapper mapper = new ObjectMapper();
    public HttpResponseDto generateModelPostRequest(GeneratorRequestDto dto, String requestUrl) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(requestUrl + BASE_URI);
            httpPost.setEntity(new StringEntity(mapper.writeValueAsString(dto)));
            httpPost.setHeader("Content-type", "application/json");
            log.info("Executing request = {} ",httpPost.getRequestLine());
            HttpResponseDto execute = httpclient.execute(httpPost, getResponseHandler());
            execute.setBaseFullUri(requestUrl+BASE_URI);
            return execute;
        } catch (IOException e) {
            throw new ServiceLogicException(ErrorCode.HTTP_REQUEST_IO_ERROR);
        }
    }

    public void deleteModelRequest(Long modelId, String requestUrl) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpDelete httpPost = new HttpDelete(requestUrl + BASE_URI + "/"+modelId);
            httpPost.setHeader("Content-type", "application/json");
            log.info("Executing request = {} ",httpPost.getRequestLine());
            httpclient.execute(httpPost, getResponseHandler());
        } catch (IOException e) {
            throw new ServiceLogicException(ErrorCode.HTTP_REQUEST_IO_ERROR);
        }
    }

    private ResponseHandler<HttpResponseDto> getResponseHandler() {
        return response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity responseBody = response.getEntity();
                if (responseBody != null) {
                    //Todo : 예외가 발생 이유 확인
//                    log.info("HTTP ResponseBody = {}",EntityUtils.toString(responseBody));
                    return mapper.readValue(
                            EntityUtils.toString(responseBody),
                            HttpResponseDto.class
                    );
                } else {
                    return null;
                }
            } else {
                //Todo : Status Code 활용하여 예외처리
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
    }

}
