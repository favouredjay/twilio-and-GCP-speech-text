package com.example.speechtotextwithtwilio;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Setter
@Getter
@Configuration
@ConfigurationProperties("twilio")
public class TwilioConfig {

        private String fromNumber;

        private String accountSid;

        private String authToken;

        @PostConstruct
        public void init(){

            Twilio.init(accountSid, authToken);

            log.info("Twilio initialized ... with account {} ", getAccountSid());
        }
    }

