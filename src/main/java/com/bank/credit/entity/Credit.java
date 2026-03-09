package com.bank.credit.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="credit")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="credit_id")
    private Long creditId;

    @Column(name="customer_id")
    private Long customerId;

    @Column
    @Enumerated(EnumType.STRING)
    private CreditType type;

    @Column
    private Double amount;

    @Column
    private Double balance;

    @Column 
    private LocalDateTime grantedDate;

    @Column
    private Short paymentsDay;

    @Column
    private Short paymentsNumber;

    @Column
    private Double paymentValue;

    @Column
    private Short paymentsDelayed;

    @Column
    private Double interestRate;

    @Column
    @Enumerated(EnumType.STRING)
    private CreditStatus status = CreditStatus.En_Revision;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable=false, updatable=false)
    private Customer customer;

    public Credit(Long creditId, Long customerId, CreditType type, Double amount, Double balance,
            LocalDateTime grantedDate, Short paymentsDay, Short paymentsNumber, Double paymentValue,
            Short paymentsDelayed, Double interestRate, CreditStatus status, Customer customer) {
        this.creditId = creditId;
        this.customerId = customerId;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.grantedDate = grantedDate;
        this.paymentsDay = paymentsDay;
        this.paymentsNumber = paymentsNumber;
        this.paymentValue = paymentValue;
        this.paymentsDelayed = paymentsDelayed;
        this.interestRate = interestRate;
        this.status = status;
        this.customer = customer;
    }

    public Credit() {
    }

    public Long getCreditId() {
        return creditId;
    }

    public void setCreditId(Long creditId) {
        this.creditId = creditId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public CreditType getType() {
        return type;
    }

    public void setType(CreditType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDateTime getGrantedDate() {
        return grantedDate;
    }

    public void setGrantedDate(LocalDateTime grantedDate) {
        this.grantedDate = grantedDate;
    }

    public Short getPaymentsDay() {
        return paymentsDay;
    }

    public void setPaymentsDay(Short paymentsDay) {
        this.paymentsDay = paymentsDay;
    }

    public Short getPaymentsNumber() {
        return paymentsNumber;
    }

    public void setPaymentsNumber(Short paymentsNumber) {
        this.paymentsNumber = paymentsNumber;
    }

    public Double getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(Double paymentValue) {
        this.paymentValue = paymentValue;
    }

    public Short getPaymentsDelayed() {
        return paymentsDelayed;
    }

    public void setPaymentsDelayed(Short paymentsDelayed) {
        this.paymentsDelayed = paymentsDelayed;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public CreditStatus getStatus() {
        return status;
    }

    public void setStatus(CreditStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public enum CreditType {
        Leasing,
        Consumo,
        Hipotecario,
        Educativo,
        Negocio
    }

    public enum CreditStatus {
        En_Revision,
        Activo,
        Cancelado,
        Mora,
        Cobro_Juridico,

    }

}
