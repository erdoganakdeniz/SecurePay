package com.techer.securepay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.mapping.ToOne;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "payment_id")
    @JsonIgnore
    private UUID id;

    private BigDecimal amount;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_card_id")
    private PaymentCard paymentCard;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    public Payment() {
    }

    private Payment(Builder builder) {
        setId(builder.id);
        setAmount(builder.amount);
        setCurrency(builder.currency);
        setPaymentCard(builder.paymentCard);
        setCustomer(builder.customer);
        setCreatedAt(builder.createdAt);
    }

    public boolean isAmountValid(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public PaymentCard getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(PaymentCard paymentCard) {
        this.paymentCard = paymentCard;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public static final class Builder {
        private UUID id;
        private BigDecimal amount;
        private Currency currency;
        private PaymentCard paymentCard;
        private Customer customer;
        private LocalDate createdAt;

        public Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder amount(BigDecimal val) {
            amount = val;
            return this;
        }

        public Builder currency(Currency val) {
            currency = val;
            return this;
        }

        public Builder paymentCard(PaymentCard val) {
            paymentCard = val;
            return this;
        }

        public Builder customer(Customer val) {
            customer = val;
            return this;
        }

        public Builder createdAt(LocalDate val) {
            createdAt = val;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
