package com.techer.securepay.dto.request;

import com.techer.securepay.entity.Currency;
import com.techer.securepay.entity.PaymentCard;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreatePaymentRequest(BigDecimal amount, Currency currency, PaymentCardRequest paymentCard,
                                   LocalDate createdAt) {
}
