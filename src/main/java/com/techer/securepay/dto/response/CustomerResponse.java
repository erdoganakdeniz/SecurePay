package com.techer.securepay.dto.response;

import java.util.UUID;

public record CustomerResponse(UUID customerId,
                               String email,
                               String phoneNo,
                               String firstName,
                               String lastName,
                               String identityNumber,
                               String country,
                               String city,
                               String zipCode,
                               String address) {
}
