package com.techer.securepay.dto.request;

import java.io.Serializable;

public record CreateCustomerRequest(String email,
                                    String phoneNo,
                                    String password,
                                    String firstName,
                                    String lastName,
                                    String identityNumber,
                                    String country,
                                    String city,
                                    String zipCode,
                                    String address) implements Serializable {


}
