package com.wirebarley.codetest.exchangeratecalc.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ConfigurationProperties("currencylayer")
public class CurrencylayerConfig {
    private String access_key;
}
