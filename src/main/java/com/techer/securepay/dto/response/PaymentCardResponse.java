package com.techer.securepay.dto.response;

import java.util.UUID;

public record PaymentCardResponse(UUID cardToken,String cardAlias,String binNumber) {
}
