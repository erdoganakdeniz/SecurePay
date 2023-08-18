package com.techer.securepay.repository;

import com.techer.securepay.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    List<Payment> findAllByCustomer_Id(UUID customerId);

    List<Payment> findAllByPaymentCard_CardNumber(String cardNumber);


    Page<Payment> findPaymentByCreatedAtBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<Payment> findPaymentByCreatedAtBetweenAndCustomer_Email(LocalDate startDate, LocalDate endDate, Pageable pageable, String customerEmail);
}
