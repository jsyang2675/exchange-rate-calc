package com.wirebarley.codetest.exchangeratecalc.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencylayerLiveApiRequest {
    private String access_key;
    private String currencies;
    private String source;
    private String format;
    private String callback;
}
