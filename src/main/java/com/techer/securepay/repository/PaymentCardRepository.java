package com.techer.securepay.repository;

import com.techer.securepay.entity.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentCardRepository extends JpaRepository<PaymentCard, UUID> {
    boolean existsById(UUID id);

    boolean existsByCardNumber(String cardNumber);


}
