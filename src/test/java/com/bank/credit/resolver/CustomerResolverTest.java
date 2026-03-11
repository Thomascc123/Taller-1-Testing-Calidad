package com.bank.credit.resolver;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.intuit.karate.junit5.Karate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class CustomerResolverTest {

    @Karate.Test
    Karate addCustomerTest() {
        return Karate.run("CreateCustomer").relativeTo(getClass());
    }


    @Karate.Test
    @Sql("/test-data/add-customer.sql")
    Karate customerByIdTest() {
        return Karate.run("CustomerById").relativeTo(getClass());
    }

    @Karate.Test
    @Sql("/test-data/add-customers.sql")
    Karate allCustomersFilledListTest() {
        return Karate.run("AllCustomers").relativeTo(getClass());
    }

    @Karate.Test
    @Sql("/test-data/empty-customers.sql")
    Karate allCustomersEmptyListTest() {
        return Karate.run("EmptyCustomerList").relativeTo(getClass());
    }

}
