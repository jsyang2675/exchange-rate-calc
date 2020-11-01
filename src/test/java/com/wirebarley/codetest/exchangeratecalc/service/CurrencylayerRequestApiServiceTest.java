package com.wirebarley.codetest.exchangeratecalc.service;

import com.wirebarley.codetest.exchangeratecalc.application.service.CurrencylayerRequestApiService;
import com.wirebarley.codetest.exchangeratecalc.infrastructure.config.CurrencylayerConfig;
import com.wirebarley.codetest.exchangeratecalc.persistence.dto.CurrencylayerLiveApiRequest;
import com.wirebarley.codetest.exchangeratecalc.persistence.dto.CurrencylayerLiveApiResponse;
import com.wirebarley.codetest.exchangeratecalc.persistence.enums.CurrenciesFromUSD;
import com.wirebarley.codetest.exchangeratecalc.persistence.enums.CurrencylayerApiEndPoint;
import com.wirebarley.codetest.exchangeratecalc.persistence.enums.FromCurrencies;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CurrencylayerRequestApiServiceTest {

    @Autowired
    private CurrencylayerConfig currencylayerConfig;
    @Autowired
    private CurrencylayerRequestApiService requestApiService;

    @Test
    void 환율정보API정상호출확인() throws URISyntaxException {
        String uri = "http://api.currencylayer.com/" + CurrencylayerApiEndPoint.live + "?";
        CurrencylayerLiveApiRequest liveApiParam =
                new CurrencylayerLiveApiRequest(
                        currencylayerConfig.getAccess_key(),
                        CurrenciesFromUSD.KRW.toString(),
                        FromCurrencies.USD.toString(), "1", "");

        ResponseEntity<CurrencylayerLiveApiResponse> apiResponse =
                requestApiService.requestCurrencylayerLiveApi(uri, liveApiParam);

        assertThat(apiResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        String quotesKey = FromCurrencies.USD.toString()+CurrenciesFromUSD.KRW.toString();
        Map resultMap = apiResponse.getBody().getQuotes();
        double exchangeRate = Double.parseDouble(resultMap.get(quotesKey).toString());

        assertThat(exchangeRate).isGreaterThan(0);
    }

}