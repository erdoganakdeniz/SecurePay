package com.techer.securepay.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "customer_id")
    private UUID id;

    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone_no", nullable = false)
    private String phoneNo;

    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "identity_no")
    private String identityNumber;

    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "zipcode")
    private String zipCode;
    @Column(name = "address")
    private String address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentCard> savedCards = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Payment> paymentList = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    public Customer() {
    }

    private Customer(Builder builder) {
        setId(builder.id);
        setEmail(builder.email);
        setPhoneNo(builder.phoneNo);
        setPassword(builder.password);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setIdentityNumber(builder.identityNumber);
        setCountry(builder.country);
        setCity(builder.city);
        setZipCode(builder.zipCode);
        setAddress(builder.address);
        setSavedCards(builder.savedCards);
        setPaymentList(builder.paymentList);
        setRegistrationDate(builder.registrationDate);
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PaymentCard> getSavedCards() {
        return savedCards;
    }

    public void setSavedCards(List<PaymentCard> savedCards) {
        this.savedCards = savedCards;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }


    public static final class Builder {
        private UUID id;
        private String email;
        private String phoneNo;
        private String password;
        private String firstName;
        private String lastName;
        private String identityNumber;
        private String country;
        private String city;
        private String zipCode;
        private String address;
        private List<PaymentCard> savedCards;
        private List<Payment> paymentList;
        private LocalDateTime registrationDate;

        public Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder phoneNo(String val) {
            phoneNo = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder identityNumber(String val) {
            identityNumber = val;
            return this;
        }

        public Builder country(String val) {
            country = val;
            return this;
        }

        public Builder city(String val) {
            city = val;
            return this;
        }

        public Builder zipCode(String val) {
            zipCode = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public Builder savedCards(List<PaymentCard> val) {
            savedCards = val;
            return this;
        }

        public Builder paymentList(List<Payment> val) {
            paymentList = val;
            return this;
        }

        public Builder registrationDate(LocalDateTime val) {
            registrationDate = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
