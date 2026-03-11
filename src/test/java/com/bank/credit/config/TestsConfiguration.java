package com.bank.credit.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;

import com.intuit.karate.junit5.Karate;

@TestConfiguration
@Profile("test")
public class TestsConfiguration {

    @Karate.Test
    Karate karateConfiguration() {
        return Karate.run("acceptance-tests")
                     .relativeTo(getClass());
    }

}
