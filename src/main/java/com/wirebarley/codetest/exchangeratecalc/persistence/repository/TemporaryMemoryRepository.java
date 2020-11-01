package com.wirebarley.codetest.exchangeratecalc.persistence.repository;

import com.wirebarley.codetest.exchangeratecalc.persistence.enums.CurrenciesFromUSD;
import com.wirebarley.codetest.exchangeratecalc.persistence.dto.ToCurrenciesResponse;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TemporaryMemoryRepository {

    private static final List<ToCurrenciesResponse> toCurrenciesList = new ArrayList<>();

    //DB구성 전 임시로 USD에서 송금 수취 가능한 국가 정보 담아두기
    public void setCurrenciesFromUSD() {
        toCurrenciesList.clear();
        for(CurrenciesFromUSD code : CurrenciesFromUSD.values()){
            toCurrenciesList.add(new ToCurrenciesResponse(code.toString(), code.getCodeName()));
        }
    }

    public List<ToCurrenciesResponse> findCurrenciesFromUSD() {
        setCurrenciesFromUSD();
        return toCurrenciesList;
    }
}
