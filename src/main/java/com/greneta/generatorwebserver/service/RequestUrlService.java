package com.greneta.generatorwebserver.service;


import com.greneta.generatorwebserver.constant.ErrorCode;
import com.greneta.generatorwebserver.entity.RequestUrl;
import com.greneta.generatorwebserver.exception.ServiceLogicException;
import com.greneta.generatorwebserver.repository.RequestUrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestUrlService {

    private final RequestUrlRepository urlRepository;

    public Boolean setUrl(String requestUrl) {
        List<RequestUrl> all = urlRepository.findAll();
        if (all.isEmpty()) {
            urlRepository.save(RequestUrl.of(requestUrl));
            return true;
        } else {
            throw new ServiceLogicException(ErrorCode.EXIST_REQUEST_URL);
        }
    }

    public Boolean deleteUrl() {
        urlRepository.deleteAll();
        return true;
    }

    public String getRequestUrl() {
        return urlRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new ServiceLogicException(ErrorCode.NOT_FOUND_REQUEST_URL))
                .getRequestServerUrl();
    }

    public Boolean isEmptyUrl() {
        List<RequestUrl> all = urlRepository.findAll();
        return all.isEmpty();
    }
}
