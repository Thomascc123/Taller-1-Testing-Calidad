package com.bank.credit.resolver;

import com.intuit.karate.junit5.Karate;

public class CustomerResolverTest {

    @Karate.Test
    Karate createCustomerTest() {
        return Karate.run("CreateCustomer").relativeTo(getClass());
    }

}
