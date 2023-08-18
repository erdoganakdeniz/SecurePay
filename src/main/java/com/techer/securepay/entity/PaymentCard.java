package com.techer.securepay.entity;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "payment_card")
public class PaymentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "card_alias")
    private String cardAlias;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "card_no")
    private String cardNumber;

    @Column(name = "exp_month")
    private String expireMonth;

    @Column(name = "exp_year")
    private String expireYear;

    @Column(name = "cvc")
    private String cvc;


    public PaymentCard() {
    }

    private PaymentCard(Builder builder) {
        setId(builder.id);
        setCardAlias(builder.cardAlias);
        setCardHolderName(builder.cardHolderName);
        setCardNumber(builder.cardNumber);
        setExpireMonth(builder.expireMonth);
        setExpireYear(builder.expireYear);
        setCvc(builder.cvc);
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCardAlias() {
        return cardAlias;
    }

    public void setCardAlias(String cardAlias) {
        this.cardAlias = cardAlias;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(String expireMonth) {
        this.expireMonth = expireMonth;
    }

    public String getExpireYear() {
        return expireYear;
    }

    public void setExpireYear(String expireYear) {
        this.expireYear = expireYear;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }


    public static final class Builder {
        private UUID id;
        private String cardAlias;
        private String cardHolderName;
        private String cardNumber;
        private String expireMonth;
        private String expireYear;
        private String cvc;

        public Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder cardAlias(String val) {
            cardAlias = val;
            return this;
        }

        public Builder cardHolderName(String val) {
            cardHolderName = val;
            return this;
        }

        public Builder cardNumber(String val) {
            cardNumber = val;
            return this;
        }

        public Builder expireMonth(String val) {
            expireMonth = val;
            return this;
        }

        public Builder expireYear(String val) {
            expireYear = val;
            return this;
        }

        public Builder cvc(String val) {
            cvc = val;
            return this;
        }

        public PaymentCard build() {
            return new PaymentCard(this);
        }
    }
}
