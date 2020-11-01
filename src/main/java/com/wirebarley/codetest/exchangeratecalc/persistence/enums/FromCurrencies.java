package com.wirebarley.codetest.exchangeratecalc.persistence.enums;

public enum FromCurrencies {
    USD("미국"),
    PHP("필리핀");
    //...
    private String codeName;
    FromCurrencies(String codeName){
        this.codeName = codeName;
    }
    public String getCodeName() {
        return this.codeName;
    }
}
