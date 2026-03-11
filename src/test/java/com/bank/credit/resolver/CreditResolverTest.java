package com.bank.credit.resolver;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.intuit.karate.junit5.Karate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class CreditResolverTest {

    @Karate.Test
    @Sql("/test-data/add-customers-for-credits.sql")
    Karate grantCreditTest() {
        return Karate.run("GrantCredit").relativeTo(getClass());
    }

}
