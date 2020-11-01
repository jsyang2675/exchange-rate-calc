package com.wirebarley.codetest.exchangeratecalc.persistence.enums;

public enum CurrenciesFromUSD {
    KRW("한국"),
    JPY("일본"),
    PHP("필리핀");
    private String codeName;
    CurrenciesFromUSD(String codeName){
        this.codeName = codeName;
    }
    public String getCodeName() {
        return this.codeName;
    }
}
