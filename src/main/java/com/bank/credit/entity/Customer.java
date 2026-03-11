package com.bank.credit.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column
    private String documentNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private CustomerStatus status = CustomerStatus.Activo;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Credit> credits;

    public Customer(Long customerId, String name, String lastName, String email, String phoneNumber, DocumentType documentType, String documentNumber, CustomerStatus status) {
        this.customerId = customerId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.status = status;
    }

    public Customer() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public enum CustomerStatus {
        Activo,
        Inactivo,
        Moroso
    }

    public enum DocumentType {
        CEDULA_CIUDADANIA("Cédula de ciudadanía"),
        PASAPORTE("Pasaporte"),
        CEDULA_EXTRANJERIA("Cédula de extranjería");

        private final String label;

        DocumentType(String label){
            this.label = label;
        }

        public String getLabel(){
            return label;
        }

        public static DocumentType fromLabel(String label){
            for (DocumentType type : values()){
                if (type.label.equals(label)) return type;
            }
            throw new IllegalArgumentException("Invalid document type "+label);
        }
    }

}
