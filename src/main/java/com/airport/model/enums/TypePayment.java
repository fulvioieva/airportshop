package com.airport.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TypePayment {
    NOT_DEFINED
    , CREDIT_CARD
    , GOOGLE_PAY
    , PAYPAL
    , BANK_TRANSFER
}
