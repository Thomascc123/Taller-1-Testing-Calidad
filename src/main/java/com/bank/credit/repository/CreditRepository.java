package com.bank.credit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.credit.entity.Credit;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

    public List<Credit> findAllCreditsByType(String type);

}
