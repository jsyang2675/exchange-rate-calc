package com.wirebarley.codetest.exchangeratecalc.application.service;

import com.wirebarley.codetest.exchangeratecalc.persistence.dto.CurrencylayerLiveApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;

@Service
public class ExchangeRateCalcService {

    /**
     * 환율 값 꺼낸 후 format return
     */
    public String calcExchangeRate(String quotesKey, ResponseEntity<CurrencylayerLiveApiResponse> apiResponse) {
        Map resultMap = apiResponse.getBody().getQuotes();
        double exchangeRate = Double.parseDouble(resultMap.get(quotesKey).toString());
        return defaultNumberFormat(exchangeRate);
    }

    /**
     * 결과 값 포맷 comma, 소수점 2자리 버림 고정
     */
    public String defaultNumberFormat(double numberData) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        String formatExchangeRate = decimalFormat.format(numberData);
        return formatExchangeRate;
    }

}
