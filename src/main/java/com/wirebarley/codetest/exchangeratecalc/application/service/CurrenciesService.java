package com.wirebarley.codetest.exchangeratecalc.application.service;

import com.wirebarley.codetest.exchangeratecalc.persistence.repository.TemporaryMemoryRepository;
import com.wirebarley.codetest.exchangeratecalc.persistence.dto.ToCurrenciesResponse;
import com.wirebarley.codetest.exchangeratecalc.persistence.enums.FromCurrencies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrenciesService {

    private final TemporaryMemoryRepository temporaryMemoryRepository;

    public CurrenciesService(TemporaryMemoryRepository temporaryMemoryRepository) {
        this.temporaryMemoryRepository = temporaryMemoryRepository;
    }

    /**
     * 송금국가별 송금 금액을 수취할 수 있는 국가 리스트 조회
     * @return
     */
    public List<ToCurrenciesResponse> findToCurrencies(FromCurrencies fromCurrencies) {
        if(fromCurrencies.equals(FromCurrencies.USD)) {
            return temporaryMemoryRepository.findCurrenciesFromUSD();
        }
        //...
        return new ArrayList<ToCurrenciesResponse>();
    }
}
