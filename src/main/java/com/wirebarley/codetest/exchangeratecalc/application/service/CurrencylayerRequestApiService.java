package com.wirebarley.codetest.exchangeratecalc.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wirebarley.codetest.exchangeratecalc.persistence.dto.CurrencylayerLiveApiRequest;
import com.wirebarley.codetest.exchangeratecalc.persistence.dto.CurrencylayerLiveApiResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;

@Service
public class CurrencylayerRequestApiService {

    /**
     * 환율정보 api 요청
     */
    public ResponseEntity<CurrencylayerLiveApiResponse> requestCurrencylayerLiveApi(
            String uri, CurrencylayerLiveApiRequest param) throws URISyntaxException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> liveApiRequestMap = objectMapper.convertValue(param, Map.class);

        Iterator<String> keyIter = liveApiRequestMap.keySet().iterator();
        while (keyIter.hasNext()) {
            String key = keyIter.next();
            uri += key+"="+liveApiRequestMap.get(key);
            if(keyIter.hasNext()) {
                uri += "&";
            }
        }

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(new URI(uri.toString()), HttpMethod.GET,
                new HttpEntity(new HttpHeaders()), CurrencylayerLiveApiResponse.class);
    }

}
