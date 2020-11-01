package com.wirebarley.codetest.exchangeratecalc.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencylayerLiveApiResponse {
    private Boolean success;
    private String terms;
    private String privacy;
    private Integer timestamp;
    private String source;
    private Map quotes;
    private Error error;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Error {
        private String code;
        private String info;
    }
}
