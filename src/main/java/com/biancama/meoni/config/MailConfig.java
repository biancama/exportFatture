package com.biancama.meoni.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by massimo.biancalani on 20/06/2017.
 */
@Component
@ConfigurationProperties(prefix = "meoni.mail")
@Setter
@Getter
@NoArgsConstructor
public class MailConfig {
    private List<String> to;
    private List<String> cc;

}
