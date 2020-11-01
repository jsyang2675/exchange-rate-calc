package com.wirebarley.codetest.exchangeratecalc.presentation.controller;

import com.wirebarley.codetest.exchangeratecalc.persistence.enums.FromCurrencies;
import com.wirebarley.codetest.exchangeratecalc.persistence.dto.ToCurrenciesResponse;
import com.wirebarley.codetest.exchangeratecalc.application.service.CurrenciesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ExchangeRateCalcViewController {

    private final CurrenciesService currenciesService;

    public ExchangeRateCalcViewController(CurrenciesService currenciesService) {
        this.currenciesService = currenciesService;
    }

    /**
     * 송금가능한 국가별 환율계산 화면 조회
     */
    @GetMapping("/calc/{currencies}")
    public String viewExchangeRateCalc(@PathVariable("currencies") String currencies, Model model) {
        try {
            FromCurrencies fromCurrencies = FromCurrencies.valueOf(currencies);
            List<ToCurrenciesResponse> toCurrenciesList = currenciesService.findToCurrencies(fromCurrencies);

            if(toCurrenciesList.isEmpty()) throw new IllegalArgumentException();

            model.addAttribute("toCurrenciesList", toCurrenciesList);
            model.addAttribute("fromCurrencies", fromCurrencies);
            model.addAttribute("fromCurrenciesName", fromCurrencies.getCodeName());
            return "exchangeRateCalc";
        }
        catch(IllegalArgumentException e) {
            model.addAttribute("errorMessage", currencies + " 코드는 지원하지 않는 국가의 통화코드 입니다.");
            return "error";
        }
        //...
        catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage","관리자에게 문의 바랍니다.");
            return "error";
        }
    }
}
