package com.bank.credit.resolver;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.bank.credit.entity.Credit;
import com.bank.credit.entity.Credit.CreditType;
import com.bank.credit.exception.InvalidCreditTypeException;
import com.bank.credit.service.CreditService;

@Controller
public class CreditResolver {
    private final CreditService creditService;

    public CreditResolver(CreditService creditService) {
        this.creditService = creditService;
    }

    @MutationMapping
    public Credit grantCredit(@Argument Long customerId, @Argument String type,
                              @Argument Double amount, @Argument Short paymentsNumber){

        type = type.replace(" ", "_");
        CreditType creditType;
        try{
            creditType = CreditType.valueOf(type);
        } catch (Exception e){
            throw new InvalidCreditTypeException();
        }
        return creditService.grantCredit(customerId, creditType, amount, paymentsNumber);
    }

}
