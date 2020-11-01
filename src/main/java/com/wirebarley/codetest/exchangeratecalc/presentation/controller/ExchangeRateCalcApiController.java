package com.wirebarley.codetest.exchangeratecalc.presentation.controller;

import com.wirebarley.codetest.exchangeratecalc.infrastructure.config.CurrencylayerConfig;
import com.wirebarley.codetest.exchangeratecalc.persistence.enums.CurrencylayerApiEndPoint;
import com.wirebarley.codetest.exchangeratecalc.persistence.dto.CurrencylayerLiveApiRequest;
import com.wirebarley.codetest.exchangeratecalc.persistence.dto.CurrencylayerLiveApiResponse;
import com.wirebarley.codetest.exchangeratecalc.application.service.CurrencylayerRequestApiService;
import com.wirebarley.codetest.exchangeratecalc.application.service.ExchangeRateCalcService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class ExchangeRateCalcApiController {

    private static final String API_BASE_URL = "http://api.currencylayer.com/";

    private final CurrencylayerRequestApiService requestApiService;
    private final ExchangeRateCalcService calcService;
    private final CurrencylayerConfig currencylayerConfig;

    public ExchangeRateCalcApiController(CurrencylayerRequestApiService requestApiService,
                                         ExchangeRateCalcService calcService,
                                         CurrencylayerConfig currencylayerConfig){
        this.requestApiService = requestApiService;
        this.calcService = calcService;
        this.currencylayerConfig = currencylayerConfig;
    }

    /**
     * 송금국가 -> 수취국가 환율정보 api call
     */
    @GetMapping("/calc-exchange-rate")
    public Result calcExchangeRate(@RequestParam String currencies,
                                   @RequestParam String source) throws URISyntaxException {

        String uri = API_BASE_URL + CurrencylayerApiEndPoint.live + "?";
        CurrencylayerLiveApiRequest liveApiParam =
                new CurrencylayerLiveApiRequest(currencylayerConfig.getAccess_key(), currencies, source, "1", "");

        ResponseEntity<CurrencylayerLiveApiResponse> apiResponse =
                requestApiService.requestCurrencylayerLiveApi(uri, liveApiParam);

        if(!HttpStatus.OK.equals(apiResponse.getStatusCode())) {
            return new Result(false, apiResponse.getStatusCode().toString(),
                    "", "", "");
        }
        if(!apiResponse.getBody().getSuccess()) {
            return new Result(false, apiResponse.getStatusCode().toString(),
                    apiResponse.getBody().getError().getCode(), apiResponse.getBody().getError().getInfo(), "");
        }
        return new Result(true, apiResponse.getStatusCode().toString(),
                "", "", calcService.calcExchangeRate(source+currencies, apiResponse));
    }

    /**
     * 환율대비 송금액으로 총 수취금액 계산
     */
    @GetMapping("/calc-receive-amount")
    public Result calcReceiveAmount(@RequestParam double exchangeRate,
                                    @RequestParam double remittanceAmount) {
        double receiveAmount = exchangeRate * remittanceAmount;
        return new Result(true, "", "", "", calcService.defaultNumberFormat(receiveAmount));
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Result<T> {
        private Boolean success;
        private String status;
        private String errorCode;
        private String message;
        private String result;
    }
}
