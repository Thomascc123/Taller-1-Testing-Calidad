package com.bank.credit.resolver;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.intuit.karate.junit5.Karate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class CustomerResolverTest {

    @Karate.Test
    Karate addCustomerTest() {
        return Karate.run("CreateCustomer").relativeTo(getClass());
    }

}
